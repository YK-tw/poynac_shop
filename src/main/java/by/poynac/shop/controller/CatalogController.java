package by.poynac.shop.controller;

import by.poynac.shop.model.AttributeFilterWrapper;
import by.poynac.shop.model.Product;
import by.poynac.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String catalog(@RequestParam(defaultValue = "0") String page,
                          @CookieValue(value = "size", defaultValue = "6") String pageSize,
                          @CookieValue(value = "sortItem", defaultValue = "def") String sortItem,
                          @CookieValue(value = "sortOption", defaultValue = "asc") String sortOption,
                          @SessionAttribute(value = "filterAttributes", required = false) List<String> attributes,
                          Model model) {
        //TODO fix filters
        Page<Product> products;
        if (attributes != null && !attributes.isEmpty()) {
            products = productRepository.findProductsByAttributes_Value(attributes.get(0),
                    PageRequest.of(Integer.parseInt(page),
                            Integer.parseInt(pageSize), filterSort(sortItem, sortOption)));
        } else {
            products = productRepository.findAll(PageRequest.of(Integer.parseInt(page),
                    Integer.parseInt(pageSize), filterSort(sortItem, sortOption)));
        }
        //TODO fix filters
        model.addAttribute("curPage", Integer.parseInt(page));
        model.addAttribute("products", products);
        model.addAttribute("size", Integer.parseInt(pageSize));
        model.addAttribute("sortItem", sortItem);
        model.addAttribute("sort", sortOption);
        model.addAttribute("wrapper", new AttributeFilterWrapper());
        return "catalog/catalog";
    }

    public Sort filterSort(String sortItem, String sortOption) {
        String sortingType;
        switch (sortItem) {
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
        return sortOption.equals("asc")
                ? Sort.by(Sort.Order.asc(sortingType))
                : Sort.by(Sort.Order.desc(sortingType));

    }

    @PostMapping
    public String setCatalogSize(HttpServletResponse response,
                                 HttpServletRequest request,
                                 @RequestParam(required = false) String size,
                                 @RequestParam(required = false) String sortItem,
                                 @RequestParam(required = false) String sortOption,
                                 @ModelAttribute(value = "wrapper") AttributeFilterWrapper wrapper) {
        if (size != null) {
            response.addCookie(new Cookie("size", size));
        }
        if (sortItem != null) {
            response.addCookie(new Cookie("sortItem", sortItem));
        }
        if (sortOption != null) {
            response.addCookie(new Cookie("sortOption", sortOption));
        }
        //TODO fix filters
        if (wrapper.getAttributes() != null && !wrapper.getAttributes().isEmpty()) {
            request.getSession().setAttribute("filterAttributes", wrapper.getAttributes());
        }
        if(wrapper.getAttributes().isEmpty()){
            request.getSession().removeAttribute("filterAttributes");
        }
        //TODO fix filters

        return "redirect:/catalog";
    }


    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        return "catalog/product";
    }

}
