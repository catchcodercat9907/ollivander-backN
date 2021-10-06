package io.ollivander.ollivanderbackend.model.dto;

import java.util.Date;

public class OrdersItemsResponse {
    private Integer id;
    private Integer ordersId;
    private String productId;
    private String title;
    private Boolean shop;
    private Double price;
    private Double discount;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    public OrdersItemsResponse() {
    }

    public OrdersItemsResponse(Integer id, Integer ordersId, String productId, String title, Boolean shop, Double price, Double discount, Integer quantity, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.ordersId = ordersId;
        this.productId = productId;
        this.title = title;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShop() {
        return shop;
    }

    public void setShop(Boolean shop) {
        this.shop = shop;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
