package com.aoher.repository.impl;

import com.aoher.model.Order;
import com.aoher.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    @Override
    public void putOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    @Override
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public Map<String, Order> getAllOrders(){
        return orders;
    }
}
