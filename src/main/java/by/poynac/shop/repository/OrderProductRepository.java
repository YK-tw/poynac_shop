package by.poynac.shop.repository;

import by.poynac.shop.model.Order;
import by.poynac.shop.model.OrderProduct;
import by.poynac.shop.model.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
}
