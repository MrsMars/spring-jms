package com.aoher.service.impl;

import com.aoher.messaging.MessageSender;
import com.aoher.model.InventoryResponse;
import com.aoher.model.Order;
import com.aoher.model.OrderStatus;
import com.aoher.repository.OrderRepository;
import com.aoher.service.OrderService;
import com.aoher.util.BasicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private MessageSender messageSender;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void sendOrder(Order order) {
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");

        order.setOrderId(BasicUtil.getUniqueId());
        order.setStatus(OrderStatus.CREATED);
        orderRepository.putOrder(order);
        LOG.info("Application : sending order request {}", order);

        messageSender.sendMessage(order);
        LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public void updateOrder(InventoryResponse response) {

        Order order = orderRepository.getOrder(response.getOrderId());

        switch (response.getReturnCode()) {
            case 200:
                order.setStatus(OrderStatus.CONFIRMED);
                break;
            case 300:
                order.setStatus(OrderStatus.FAILED);
                break;
            default:
                order.setStatus(OrderStatus.PENDING);
                break;
        }
        orderRepository.putOrder(order);
    }

    public Map<String, Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }
}