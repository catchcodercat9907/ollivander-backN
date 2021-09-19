package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    @Column(name = "sessionId")
    private Integer sessionId;

    @Column(name = "token")
    private String token;

    @Column(name = "status")
    private Integer status;

    @Column(name = "subTotal")
    private Float subTotal;

    @Column(name = "itemDiscount")
    private Float itemDiscount;

    @Column(name = "tax")
    private Float tax;

    @Column(name = "shipping")
    private Float shipping;

    @Column(name = "total")
    private Float total;

    @Column(name = "promo")
    private String promo;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "grandTotal")
    private Float grandTotal;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content")
    private String content;

    public Order() {
    }

    public Order(Integer id, Account account, Integer sessionId, String token, Integer status, Float subTotal, Float itemDiscount, Float tax, Float shipping, Float total, String promo, Float discount, Float grandTotal, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
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

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(Float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
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
