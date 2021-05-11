package by.poynac.shop.controller;

import by.poynac.shop.model.wrapper.AttributeFilterWrapper;
import by.poynac.shop.model.Product;
import by.poynac.shop.model.wrapper.OrderSaveWrapper;
import by.poynac.shop.model.wrapper.SessionOrderWrapper;
import by.poynac.shop.service.OrderService;
import by.poynac.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("wrapper", generateMainPageWrapper());
        return "home/home";
    }

    private AttributeFilterWrapper generateMainPageWrapper() {
        AttributeFilterWrapper wr = new AttributeFilterWrapper();
        List<String> attr = new ArrayList<>();
        attr.add("earrings");
        attr.add("ring");
        attr.add("chain");
        attr.add("pendant");
        attr.add("cuff");
        attr.add("bracelet");
        wr.setAttributes(attr);
        return wr;
    }

    @GetMapping("/basket")
    public String basket(Model model,
                         @SessionAttribute(value = "order", required = false) SessionOrderWrapper order) {

        if (order != null) {
            model.addAttribute("order", order);
        }
        model.addAttribute("saveParam", new OrderSaveWrapper());
        return "home/basket";
    }

    @GetMapping("/basket/remove/{id:[\\d]+}")
    public String basketRemoveItem(HttpServletRequest request,
                                   Model model,
                                   @SessionAttribute(value = "order", required = false) SessionOrderWrapper order,
                                   @PathVariable Long id) {

        if (order != null) {
            order.setProductAmount(productService.findById(id), 0);
            request.getSession().setAttribute("order", order);
            model.addAttribute("order", order);
        }
        return "redirect:/basket";
    }

    @PostMapping("/basket/{id:[\\d]+}")
    public String changeBasketItemAmount(HttpServletRequest request,
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
        return "redirect:/basket";
    }

    @PostMapping("/basket/save")
    public String saveOrder(HttpServletRequest request,
                            Model model,
                            @ModelAttribute(value = "saveParam") OrderSaveWrapper saveParam) {

        orderService.saveOrder((SessionOrderWrapper) request.getSession().getAttribute("order"), saveParam);
        request.getSession().removeAttribute("order");

        return "redirect:/";
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "/home/profile";
    }

}
