package io.ollivander.ollivanderbackend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ollivander.ollivanderbackend.model.DbConst;
import io.ollivander.ollivanderbackend.utils.DataTransferObjectHelper;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
public class Product {

    public static final String[] BASIC_PROPS = {DbConst.ID, DbConst.TITLE, DbConst.META_TITLE, DbConst.SLUG,
            DbConst.TYPE, DbConst.PRICE, DbConst.DISCOUNT, DbConst.QUANTITY, DbConst.SHOP, DbConst.URL,
            DbConst.UPDATED_AT, DbConst.PUBLISHED_AT, DbConst.STARTS_AT, DbConst.ENDS_AT};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "title")
    private String title;

    @Column(name = "metaTitle")
    private String metaTitle;

    @Column(name = "slug")
    private String slug;

    @Column(name = "summary")
    private String summary;

    @Column(name = "type", columnDefinition = "SMALLINT")
    private Integer type;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price")
    private Float price;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "quantity", columnDefinition = "SMALLINT")
    private Integer quantity;

    @Column(name = "shop", columnDefinition = "TINYINT")
    private Boolean shop;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "publishedAt")
    private Date publishedAt;

    @Column(name = "startsAt")
    private Date startsAt;

    @Column(name = "endsAt")
    private Date endsAt;

    @Column(name = "content")
    @Lob
    private String content;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "category_id", nullable = false, updatable = true)})
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_tag", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "tag_id", nullable = false, updatable = true)})
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductMeta> productMeta;

    public Product(Integer id, Account account, String title, String metaTitle, String slug, String summary, Integer type, String sku, Float price, Float discount, Integer quantity, Boolean shop, Date createdAt, Date updatedAt, Date publishedAt, Date startsAt, Date endsAt, String content, Set<Category> categories, Set<Tag> tags, Set<ProductMeta> productMeta) {
        this.id = id;
        this.account = account;
        this.title = title;
        this.metaTitle = metaTitle;
        this.slug = slug;
        this.summary = summary;
        this.type = type;
        this.sku = sku;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.shop = shop;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.content = content;
        this.categories = categories;
        this.tags = tags;
        this.productMeta = productMeta;
    }

    public Product() {
    }

    @Transient
    public Map<Object, Object> toSimpleObject() {
        Map<Object, Object> mProduct = DataTransferObjectHelper.simplify(this, BASIC_PROPS);
        return mProduct;
    }

    public Set<ProductMeta> getProductMeta() {
        return productMeta;
    }

    public void setProductMeta(Set<ProductMeta> productMeta) {
        this.productMeta = productMeta;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getShop() {
        return shop;
    }

    public void setShop(Boolean shop) {
        this.shop = shop;
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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
