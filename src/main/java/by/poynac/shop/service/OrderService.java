package by.poynac.shop.service;

import by.poynac.shop.model.wrapper.OrderSaveWrapper;
import by.poynac.shop.model.wrapper.SessionOrderWrapper;

public interface OrderService {

    void saveOrder(SessionOrderWrapper wrapper, OrderSaveWrapper saveParam);

}
