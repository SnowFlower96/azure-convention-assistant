package com.example.manager.repository;

import com.example.manager.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static final String FILE_PATH = "src/main/resources/orders.json";
    private final ObjectMapper mapper = new ObjectMapper();

    private List<Order> load() throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        return mapper.readValue(file, new TypeReference<List<Order>>() {});
    }

    private void save(List<Order> list) throws Exception {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), list);
    }

    @Override
    public List<Order> findAll() throws Exception {
        return load();
    }

    @Override
    public Order findById(int id) throws Exception {
        return load().stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Order insert(Order order) throws Exception {
        List<Order> list = load();
        int nextId = list.stream().mapToInt(Order::getId).max().orElse(0) + 1;
        order.setId(nextId);
        list.add(order);
        save(list);
        return order;
    }

    @Override
    public boolean delete(int id) throws Exception {
        List<Order> list = load();
        boolean removed = list.removeIf(o -> o.getId() == id);
        if (removed) save(list);
        return removed;
    }
}
