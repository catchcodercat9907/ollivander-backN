package io.ollivander.ollivanderbackend.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders_item")
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

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

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "content")
    @Lob
    private String content;

    public OrdersItem() {
    }

    public OrdersItem(Integer id, Orders orders, Product product, String sku, Double price, Double discount, Integer quantity, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.orders = orders;
        this.product = product;
        this.sku = sku;
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

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
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
