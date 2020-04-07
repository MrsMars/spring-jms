package com.aoher.controller;

import com.aoher.model.Order;
import com.aoher.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.aoher.util.Constants.*;

@Controller
public class AppController {

    @Autowired
    private OrderService orderService;

    @GetMapping({"/", "/home"})
    public String prepareProduct(ModelMap model) {
        return INDEX_VIEW;
    }

    @GetMapping("/newOrder")
    public String prepareOrder(ModelMap model) {
        Order order = new Order();
        model.addAttribute(ORDER_ATTRIBUTE, order);
        return ORDER_VIEW;
    }

    @PostMapping("/newOrder")
    public String sendOrder(@Valid Order order, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return ORDER_VIEW;
        }
        orderService.sendOrder(order);
        model.addAttribute(SUCCESS_ATTRIBUTE, String.format("Order for %s registered.", order.getProductName()));
        return ORDER_SUCCESS_VIEW;
    }

    @GetMapping("/checkStatus")
    public String checkOrderStatus(ModelMap model) {
        model.addAttribute(ORDERS_ATTRIBUTE, orderService.getAllOrders());
        return ORDER_STATUS_VIEW;
    }
}
