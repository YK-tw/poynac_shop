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
                          @CookieValue(value = "sortOption", defaultValue = "asc") String sortOption,
                          Model model) {
        Page<Product> products = productRepository.findAll(PageRequest.of(Integer.parseInt(page),
                Integer.parseInt(pageSize), filterSort(filter, sortOption)));
        model.addAttribute("curPage", Integer.parseInt(page));
        model.addAttribute("products", products);
        model.addAttribute("size", Integer.parseInt(pageSize));
        model.addAttribute("filter", filter);
        model.addAttribute("sort", sortOption);
        return "catalog/catalog";
    }

    public Sort filterSort(String filter, String sortOption) {
        String sortingType;
        switch (filter) {
            case "alphabet":
                sortingType = "name";
                break;
            case "price":
                sortingType = "price";
                break;
            default:
                sortingType = "id";
                break;
        }
        if (sortOption.equals("asc")) {
            return Sort.by(Sort.Order.asc(sortingType));
        } else {
            return Sort.by(Sort.Order.desc(sortingType));
        }
    }

    @PostMapping
    public String setCatalogSize(HttpServletResponse response,
                                 @RequestParam(required = false) String size,
                                 @RequestParam(required = false) String filter,
                                 @RequestParam(required = false) String sortOption) {
        if (size != null) {
            response.addCookie(new Cookie("size", size));
        }
        if (filter != null) {
            response.addCookie(new Cookie("filter", filter));
        }
        if (sortOption != null) {
            response.addCookie(new Cookie("sortOption", sortOption));
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
