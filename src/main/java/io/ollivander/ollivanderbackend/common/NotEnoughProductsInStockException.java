package io.ollivander.ollivanderbackend.common;

import io.ollivander.ollivanderbackend.model.entities.Product;
import io.ollivander.ollivanderbackend.model.entities.ProductMeta;

public class NotEnoughProductsInStockException extends Exception{
    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getTitle(), product.getQuantity()));
    }
}
