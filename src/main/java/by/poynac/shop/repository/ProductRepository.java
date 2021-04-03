package by.poynac.shop.repository;

import by.poynac.shop.model.Attribute;
import by.poynac.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findDistinctByAttributesIn(List<Attribute> values, Pageable pageable);

}
