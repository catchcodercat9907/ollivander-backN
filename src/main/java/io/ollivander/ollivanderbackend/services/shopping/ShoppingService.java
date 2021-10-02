package io.ollivander.ollivanderbackend.services.shopping;

import io.ollivander.ollivanderbackend.common.NotEnoughProductsInStockException;
import io.ollivander.ollivanderbackend.model.entities.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    Double getTotal();
}
