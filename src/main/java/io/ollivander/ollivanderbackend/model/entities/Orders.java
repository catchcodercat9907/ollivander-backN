package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "token")
    private String token;

    @Column(name = "status", columnDefinition = "SMALLINT")
    private Integer status;

    @Column(name = "subTotal")
    private Double subTotal;

    @Column(name = "itemDiscount")
    private Double itemDiscount;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "shipping")
    private Double shipping;

    @Column(name = "total")
    private Double total;

    @Column(name = "promo")
    private String promo;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "grandTotal")
    private Double grandTotal;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content")
    @Lob
    private String content;

    public Orders() {
    }

    public Orders(Integer id, Account account, String sessionId, String token, Integer status, Double subTotal, Double itemDiscount, Double tax, Double shipping, Double total, String promo, Double discount, Double grandTotal, Date createdAt, Date updatedAt, String content) {
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
