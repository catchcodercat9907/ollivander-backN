package io.ollivander.ollivanderbackend.services.shopping;

import io.ollivander.ollivanderbackend.common.NotEnoughProductsInStockException;
import io.ollivander.ollivanderbackend.model.entities.Product;
import io.ollivander.ollivanderbackend.model.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingServiceImpl implements ShoppingService {

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    private ProductRepository productRepository;

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
}
