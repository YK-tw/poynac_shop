package by.poynac.shop.controller;

import by.poynac.shop.model.Product;
import by.poynac.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String catalog(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "catalog/catalog";
    }

    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        return "catalog/product";
    }

}
