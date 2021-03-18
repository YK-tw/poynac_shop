package by.poynac.shop.controller;

import by.poynac.shop.model.Product;
import by.poynac.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String catalog(Model model) {
        Iterable<Product> products = productRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("name"))));
        model.addAttribute("products", products);
        return "catalog/catalog";
    }

    @GetMapping("/{id:[\\d]+}")
    public String product(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        model.addAttribute("product", product.get());
        return "catalog/product";
    }

}
