package com.aoher.repository;

import com.aoher.model.Order;

import java.util.Map;

public interface OrderRepository {

    void putOrder(Order order);
    Order getOrder(String orderId);
    Map<String, Order> getAllOrders();
}