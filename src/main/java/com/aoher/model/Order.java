package com.aoher.model;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private String productName;
    private int quantity;
    private OrderStatus status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(productName, order.productName) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productName, quantity, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }
}
