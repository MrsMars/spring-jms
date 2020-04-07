package com.aoher.model;

import java.io.Serializable;
import java.util.Objects;

public class InventoryResponse implements Serializable {

    private static final long serialVersionUID = -9032086315603687948L;

    private String orderId;
    private int returnCode;
    private String comment;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryResponse that = (InventoryResponse) o;
        return returnCode == that.returnCode &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, returnCode, comment);
    }

    @Override
    public String toString() {
        return "InventoryResponse{" +
                "orderId='" + orderId + '\'' +
                ", returnCode=" + returnCode +
                ", comment='" + comment + '\'' +
                '}';
    }
}