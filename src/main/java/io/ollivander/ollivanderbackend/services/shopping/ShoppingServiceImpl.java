package io.ollivander.ollivanderbackend.services.shopping;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.common.NotEnoughProductsInStockException;
import io.ollivander.ollivanderbackend.model.dto.CartItemRequest;
import io.ollivander.ollivanderbackend.model.dto.CartItemResponse;
import io.ollivander.ollivanderbackend.model.dto.CartResponse;
import io.ollivander.ollivanderbackend.model.dto.OrdersResponse;
import io.ollivander.ollivanderbackend.model.entities.*;
import io.ollivander.ollivanderbackend.model.repos.CartRepository;
import io.ollivander.ollivanderbackend.model.repos.OrdersRepository;
import io.ollivander.ollivanderbackend.model.repos.ProductRepository;
import io.ollivander.ollivanderbackend.utils.SecurityContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.getOne(entry.getKey().getId());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
        productRepository.saveAll(products.keySet());
        productRepository.flush();
        products.clear();
    }

    @Override
    public Double getTotal() {

        return null;
    }

    @Override
    public CartResponse getAccountCart(Account account) throws BaseException {
        account = SecurityContextHelper.getCurrentAccount();
        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }
        CartResponse response = cartRepo.getAccountCart(account.getId());

        return response;
    }

    public CartItemResponse getAccountCartItem(Account account) throws BaseException {
        return null;
    }

    @Override
    public CartResponse createCart(Account account) {
        return new CartResponse();
    }

    @Override
    public OrdersResponse createOrders(List<CartItemRequest> request) {
        Account account = SecurityContextHelper.getCurrentAccount();
        Orders ord = ordersRepo.findByAccount(account);
        List<OrdersItem> ordItems = new ArrayList<>();
        if (ord == null) {
            ord = new Orders();
            ord.setAccount(account);
        } else {

        }
        return null;
    }
}
