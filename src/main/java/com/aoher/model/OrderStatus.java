package com.aoher.model;

public enum OrderStatus {

    CREATED("Created"),
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    FAILED("Failed");

    private String status;

    OrderStatus(final String status) {
        this.status = status;
    }

    public String getName(){
        return name();
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
