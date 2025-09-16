package com.example.manager.repository;

import com.example.manager.model.Order;
import java.util.List;

public interface OrderRepository {
    List<Order> findAll() throws Exception;
    Order findById(int id) throws Exception;
    Order insert(Order order) throws Exception;
    boolean delete(int id) throws Exception;
}
