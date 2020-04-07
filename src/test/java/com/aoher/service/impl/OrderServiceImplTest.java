package com.aoher.service.impl;

import com.aoher.config.SpringTestConfig;
import com.aoher.model.Order;
import com.aoher.service.OrderService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class, loader = AnnotationConfigContextLoader.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() {
        assertNotNull(orderService);
    }

    @Test
    @Ignore
    public void testSendOrder() {
        String name = "MyFirstProd";

        Order order = new Order();
        order.setProductName(name);
        order.setQuantity(1);

        orderService.sendOrder(order);
        Map<String, Order> orderMap = orderService.getAllOrders();

        Order result = getOrderByName(name, orderMap);
        assertNotNull(result);
        assertEquals(name, result.getProductName());
    }

    @Test
    public void getAllOrders() {
        Map<String, Order> orders = orderService.getAllOrders();
        assertNotNull(orders);
    }

    private Order getOrderByName(String name, Map<String, Order> orderMap) {
        Set<String> keys = orderMap.keySet();
        return keys.stream().map(orderMap::get).filter(order -> order.getProductName().equals(name)).findFirst().orElse(null);
    }
}