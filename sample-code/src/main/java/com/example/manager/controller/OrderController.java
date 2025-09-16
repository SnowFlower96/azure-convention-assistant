package com.example.manager.controller;

import com.example.manager.model.Order;
import com.example.manager.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    // ===========================================
    // 필드
    // ===========================================
    private final OrderService service;

    // ===========================================
    // Getter / Setter
    // ===========================================
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> list() throws Exception {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order get(@PathVariable int id) throws Exception {
        return service.getOrderById(id);
    }

    @PostMapping
    public Order create(@RequestBody Order order) throws Exception {
        return service.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) throws Exception {
        return service.deleteOrder(id);
    }
}
