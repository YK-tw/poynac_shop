package by.poynac.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping("/")
    public String catalog(Model model) {
        return "catalog/catalog";
    }

    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        return "catalog/product";
    }

}
