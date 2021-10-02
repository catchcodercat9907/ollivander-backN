package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private Double price;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "quantity", columnDefinition = "SMALLINT")
    private Integer quantity;

    @Column(name = "active", columnDefinition = "TINYINT")
    private Boolean active;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content")
    @Lob
    private String content;

    public CartItem() {
    }

    public CartItem(Integer id, Cart cart, Product product, String sku, Double price, Double discount, Integer quantity, Boolean active, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.sku = sku;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.active = active;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
