package io.ollivander.ollivanderbackend.model.dto;

import io.ollivander.ollivanderbackend.model.entities.Product;

import java.util.Date;

public class CartItemResponse {
    private Integer id;
    private Integer cartId;
    private String productId;
    private String title;
    private Boolean shop;
    private Double price;
    private Double discount;
    private Integer quantity;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    public CartItemResponse() {
    }

    public CartItemResponse(Integer id, Integer cartId, String productId, String title, Boolean shop, Double price, Double discount, Integer quantity, Boolean active, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.title = title;
        this.shop = shop;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
