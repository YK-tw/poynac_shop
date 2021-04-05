package by.poynac.shop.controller;

import by.poynac.shop.model.AttributeFilterWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        AttributeFilterWrapper wr = new AttributeFilterWrapper();
        List<String> attr = new ArrayList<>();
        attr.add("earrings");
        attr.add("ring");
        attr.add("chain");
        attr.add("pendant");
        attr.add("cuff");
        attr.add("bracelet");
        wr.setAttributes(attr);
        model.addAttribute("wrapper", wr);
        return "home/home";
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        return "home/basket";
    }

}
