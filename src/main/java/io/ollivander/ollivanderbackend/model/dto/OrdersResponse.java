package io.ollivander.ollivanderbackend.model.dto;

import java.util.Date;

public class OrdersResponse {
    private Integer id;
    private Integer accountId;
    private String sessionId;
    private String token;
    private Integer status;
    private Double subTotal;
    private Double itemDiscount;
    private Double tax;
    private Double shipping;
    private Double total;
    private String promo;
    private Double discount;
    private Double grandTotal;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    public OrdersResponse() {
    }

    public OrdersResponse(Integer id, Integer accountId, String sessionId, String token, Integer status, Double subTotal, Double itemDiscount, Double tax, Double shipping, Double total, String promo, Double discount, Double grandTotal, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.accountId = accountId;
        this.sessionId = sessionId;
        this.token = token;
        this.status = status;
        this.subTotal = subTotal;
        this.itemDiscount = itemDiscount;
        this.tax = tax;
        this.shipping = shipping;
        this.total = total;
        this.promo = promo;
        this.discount = discount;
        this.grandTotal = grandTotal;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(Double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
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
