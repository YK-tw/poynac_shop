package by.poynac.shop.service;

import by.poynac.shop.model.*;
import by.poynac.shop.model.wrapper.OrderSaveWrapper;
import by.poynac.shop.model.wrapper.SessionOrderWrapper;
import by.poynac.shop.repository.OrderRepository;
import by.poynac.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveOrder(SessionOrderWrapper orderWrapper, OrderSaveWrapper userDataWrapper) {

        if (orderWrapper == null) {
            return;
        }

        Set<Product> products = orderWrapper.getProducts().keySet();
        User currentUser = userRepository.findByEmail(((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername());
        Order order = new Order();
        List<OrderProduct> orderProductList = new ArrayList<>();
        for (Product p : products) {
            OrderProduct temp = new OrderProduct();
            temp.setId(new OrderProductKey(order.getId(), p.getId()));
            temp.setOrder(order);
            temp.setProduct(p);
            temp.setAmount(orderWrapper.getProducts().get(p));
            orderProductList.add(temp);
        }
        order.setOrderProducts(orderProductList);
//        order.setDate(Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
        order.setUserId(currentUser.getId());
        order.setPrice(orderWrapper.countFullPrice());
        order.setComment(userDataWrapper.getComment());
        order.setDelivery(userDataWrapper.getDelivery());
        orderRepository.save(order);
    }
}
