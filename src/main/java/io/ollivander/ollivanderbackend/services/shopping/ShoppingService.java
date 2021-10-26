package io.ollivander.ollivanderbackend.services.shopping;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.NotEnoughProductsInStockException;
import io.ollivander.ollivanderbackend.model.dto.CartItemRequest;
import io.ollivander.ollivanderbackend.model.dto.CartItemResponse;
import io.ollivander.ollivanderbackend.model.dto.CartResponse;
import io.ollivander.ollivanderbackend.model.dto.OrdersResponse;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Product;

import java.util.List;
import java.util.Map;

public interface ShoppingService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    Double getTotal();

    CartResponse getAccountCart(Account account) throws BaseException;

    CartItemResponse getAccountCartItem(Account account) throws BaseException;

    CartResponse createCart(Account account);

    void createOrders(List<CartItemRequest> request) throws BaseException;

    void updateOrders(List<CartItemRequest> request) throws BaseException;
}
