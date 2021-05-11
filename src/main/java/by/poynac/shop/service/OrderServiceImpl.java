package by.poynac.shop.service;

import by.poynac.shop.model.*;
import by.poynac.shop.model.wrapper.OrderSaveWrapper;
import by.poynac.shop.model.wrapper.SessionOrderWrapper;
import by.poynac.shop.repository.OrderProductRepository;
import by.poynac.shop.repository.OrderRepository;
import by.poynac.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

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
        Set<OrderProduct> orderProductSet = new HashSet<>();
        for (Product p : products) {
            OrderProduct temp = new OrderProduct();
            temp.setId(new OrderProductKey(order.getId(), p.getId()));
            temp.setOrder(order);
            temp.setProduct(p);
            temp.setAmount(orderWrapper.getProducts().get(p));
            orderProductSet.add(temp);
            orderProductRepository.save(temp);
        }
        order.setProductSet(orderProductSet);
        order.setDate(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())));
        order.setUserId(currentUser.getId());
        order.setPrice(orderWrapper.countFullPrice());
        order.setComment(userDataWrapper.getComment());
        order.setDelivery(userDataWrapper.getDelivery());
        orderRepository.save(order);
    }
}
