package by.poynac.shop.repository;

import by.poynac.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from Product p join p.attributes attr where attr.value in (:values) group by p having count(attr)=:attr_count")
    Page<Product> findDistinctByAllAttributes(@Param("values") List<String> values, @Param("attr_count") Long size, Pageable pageable);

}
