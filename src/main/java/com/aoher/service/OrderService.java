package com.aoher.service;

import com.aoher.model.InventoryResponse;
import com.aoher.model.Order;

import java.util.Map;

public interface OrderService {

    void sendOrder(Order order);
    void updateOrder(InventoryResponse response);
    Map<String, Order> getAllOrders();
}
