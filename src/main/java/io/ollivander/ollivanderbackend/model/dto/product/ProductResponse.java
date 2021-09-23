package io.ollivander.ollivanderbackend.model.dto.product;

public class ProductResponse {
    private Integer productId;
    private Integer accountId;
    private String title;
    private String metaTitle;
    private String slug;
    private Float price;
    private Float discount;
    private Integer quantity;
    private Boolean shop;
    private String url;

    public ProductResponse() {
    }

    public ProductResponse(Integer productId, Integer accountId, String title, String metaTitle, String slug, Float price, Float discount, Integer quantity, Boolean shop, String url) {
        this.productId = productId;
        this.accountId = accountId;
        this.title = title;
        this.metaTitle = metaTitle;
        this.slug = slug;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.shop = shop;
        this.url = url;
    }

}
