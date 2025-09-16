package com.example.manager.service;

import com.example.manager.model.Order;
import java.util.List;

public interface OrderService {

    public abstract List<Order> getAllOrders() throws Exception ;
    public abstract Order getOrderById(int id) throws Exception;
    public abstract Order createOrder(Order order) throws Exception;
    public abstract boolean deleteOrder(int id) throws Exception;
}
