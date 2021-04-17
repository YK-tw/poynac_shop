package by.poynac.shop.controller;

import by.poynac.shop.model.AttributeFilterWrapper;
import by.poynac.shop.model.Product;
import by.poynac.shop.model.SessionOrderWrapper;
import by.poynac.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        Pageable pageSettings = generateCatalogPageSettings(page, pageSize, sortItem, sortOption);

        if (attributes != null && !attributes.isEmpty()) {
            products = productService.findProductsByAttributesValues(attributes, pageSettings);
            wrapper.setAttributes(attributes);
        } else {
            products = productService.findAll(pageSettings);
        }

        model.addAttribute("curPage", Integer.parseInt(page));
        model.addAttribute("products", products);
        model.addAttribute("size", Integer.parseInt(pageSize));
        model.addAttribute("sortItem", sortItem);
        model.addAttribute("sort", sortOption);
        model.addAttribute("wrapper", wrapper);

        return "catalog/catalog";
    }

    private Pageable generateCatalogPageSettings(String page, String pageSize, String sortItem, String sortOption) {
        return PageRequest.of(Integer.parseInt(page),
                Integer.parseInt(pageSize), filterSort(sortItem, sortOption));
    }

    private Sort filterSort(String sortItem, String sortOption) {
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
    public String setLonglivingValues(HttpServletResponse response,
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
        if (wrapper.getAttributes() != null) {
            request.getSession().setAttribute("filterAttributes", wrapper.getAttributes());
        }

        return "redirect:/catalog";
    }


    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("looked_products", new ArrayList<Product>());
        return "catalog/product";
    }

    @PostMapping("/{id:[\\d]+}")
    public String changeBasket(HttpServletRequest request,
                               @ModelAttribute(value = "toggle") String toggle,
                               Model model,
                               @PathVariable long id,
                               @SessionAttribute(value = "order", required = false) SessionOrderWrapper order) {

        Product product = productService.findById(id);
        HttpSession session = request.getSession();

        if (order == null) {
            order = new SessionOrderWrapper();
        }

        order.setProductAmount(product, Integer.parseInt(toggle));

        session.setAttribute("order", order);
        model.addAttribute("product", product);
        return "catalog/product";
    }

}
