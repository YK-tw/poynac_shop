package by.poynac.shop.service;

import by.poynac.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findProductsByAttributesValues(List<String> values, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

}
