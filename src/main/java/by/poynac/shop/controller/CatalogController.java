package by.poynac.shop.controller;

import by.poynac.shop.model.AttributeFilterWrapper;
import by.poynac.shop.model.Product;
import by.poynac.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String catalog(@RequestParam(defaultValue = "0") String page,
                          @CookieValue(value = "size", defaultValue = "6") String pageSize,
                          @CookieValue(value = "sortItem", defaultValue = "def") String sortItem,
                          @CookieValue(value = "sortOption", defaultValue = "asc") String sortOption,
                          @SessionAttribute(value = "filterAttributes", required = false) List<String> attributes,
                          Model model) {
        Page<Product> products;
        AttributeFilterWrapper wrapper = new AttributeFilterWrapper();
        if (attributes != null && !attributes.isEmpty()) {
            products = productService.findProductsByAttributesValues(attributes,
                    PageRequest.of(Integer.parseInt(page),
                            Integer.parseInt(pageSize), filterSort(sortItem, sortOption)));
            wrapper.setAttributes(attributes);
        } else {
            products = productService.findAll(PageRequest.of(Integer.parseInt(page),
                    Integer.parseInt(pageSize), filterSort(sortItem, sortOption)));
        }
        model.addAttribute("curPage", Integer.parseInt(page));
        model.addAttribute("products", products);
        model.addAttribute("size", Integer.parseInt(pageSize));
        model.addAttribute("sortItem", sortItem);
        model.addAttribute("sort", sortOption);
        model.addAttribute("wrapper", wrapper);
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
        if (wrapper.getAttributes() != null && !wrapper.getAttributes().isEmpty()) {
            request.getSession().setAttribute("filterAttributes", wrapper.getAttributes());
        } else {
            request.getSession().removeAttribute("filterAttributes");
        }

        return "redirect:/catalog";
    }


    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "catalog/product";
    }

}
