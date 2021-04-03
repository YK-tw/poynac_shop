package by.poynac.shop.service;

import by.poynac.shop.model.Product;
import by.poynac.shop.repository.AttributeRepository;
import by.poynac.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Page<Product> findProductsByAttributesValues(List<String> values, Pageable pageable) {
        return productRepository.findDistinctByAttributesIn(
                attributeRepository.findAttributesByValueIn(values), pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
