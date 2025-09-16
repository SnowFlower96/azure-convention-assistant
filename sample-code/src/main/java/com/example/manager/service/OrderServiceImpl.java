package com.example.manager.service;

import com.example.manager.model.Order;
import com.example.manager.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repo;

    public OrderServiceImpl(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getAllOrders() throws Exception {
        return repo.findAll();
    }

    public Order getOrderById(int id) throws Exception {
        Order order = repo.findById(id);
        if (order == null) throw new RuntimeException("Order not found");
        return order;
    }

    public Order createOrder(Order order) throws Exception {
        return repo.insert(order);
    }

    public boolean deleteOrder(int id) throws Exception {
        boolean deleted = repo.delete(id);
        if (!deleted) throw new RuntimeException("Order not found");
        return deleted;
    }
}
