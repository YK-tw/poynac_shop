package by.poynac.shop.repository;

import by.poynac.shop.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> findAttributesByValueIn(List<String> values);

}
