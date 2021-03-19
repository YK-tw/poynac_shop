package by.poynac.shop.controller;

import by.poynac.shop.model.Product;
import by.poynac.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String catalog(@RequestParam(defaultValue = "0") String page,
                          @CookieValue(value = "size", defaultValue = "6") String pageSize,
                          @CookieValue(value = "filter", defaultValue = "def") String filter,
                          Model model) {
        Page<Product> products = productRepository.findAll(PageRequest.of(Integer.parseInt(page),
                Integer.parseInt(pageSize), filterSort(filter)));
        model.addAttribute("curPage", Integer.parseInt(page));
        model.addAttribute("products", products);
        model.addAttribute("size", Integer.parseInt(pageSize));
        model.addAttribute("filter", filter);
        return "catalog/catalog";
    }

    public Sort filterSort(String filter) {
        switch (filter) {
            case "alphabet":
                return Sort.by(Sort.Order.asc("name"));
            case "price":
                return Sort.by(Sort.Order.asc("price"));
            default:
                return Sort.by(Sort.Order.asc("id"));
        }
    }

    @PostMapping
    public String setCatalogSize(HttpServletResponse response,
                                 @RequestParam(required = false) String size,
                                 @RequestParam(required = false) String filter) {
        if (size != null) {
            response.addCookie(new Cookie("size", size));
        }
        if (filter != null) {
            response.addCookie(new Cookie("filter", filter));
        }
        return "redirect:/catalog";
    }


    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        return "catalog/product";
    }

}
