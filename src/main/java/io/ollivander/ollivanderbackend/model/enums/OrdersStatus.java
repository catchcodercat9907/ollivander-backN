package io.ollivander.ollivanderbackend.model.enums;

public enum OrdersStatus {
    NEW(1, "Order New"),
    CHECKOUT(2, "Order Checkout"),
    PAID(3, "Order Paid"),
    FAILED(4, "Order Failed"),
    SHIPPED(5, "Order Shipped"),
    DELIVERED(6, "Order Delivered"),
    RETURNED(7, "Order Returned"),
    COMPLETE(8, "Order Complete");

    private int code;
    private String name;

    OrdersStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
