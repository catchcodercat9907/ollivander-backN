package io.ollivander.ollivanderbackend.services.shopping;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.common.NotEnoughProductsInStockException;
import io.ollivander.ollivanderbackend.model.dto.CartItemRequest;
import io.ollivander.ollivanderbackend.model.dto.CartItemResponse;
import io.ollivander.ollivanderbackend.model.dto.CartResponse;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Orders;
import io.ollivander.ollivanderbackend.model.entities.OrdersItem;
import io.ollivander.ollivanderbackend.model.entities.Product;
import io.ollivander.ollivanderbackend.model.enums.OrdersStatus;
import io.ollivander.ollivanderbackend.model.repos.CartRepository;
import io.ollivander.ollivanderbackend.model.repos.OrdersItemsRepository;
import io.ollivander.ollivanderbackend.model.repos.OrdersRepository;
import io.ollivander.ollivanderbackend.model.repos.ProductRepository;
import io.ollivander.ollivanderbackend.utils.SecurityContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private OrdersRepository ordersRepo;

    @Autowired
    private OrdersItemsRepository ordersItemRepo;

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
            product = productRepo.getOne(entry.getKey().getId());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
        productRepo.saveAll(products.keySet());
        productRepo.flush();
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
    public void createOrders(List<CartItemRequest> request) throws BaseException {
        Account account = SecurityContextHelper.getCurrentAccount();
        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }
        Orders o = new Orders();
        o.setAccount(account);
        List<OrdersItem> oItems = new ArrayList<>();
        request.forEach(item -> {
            OrdersItem oItem = new OrdersItem();
            Product p = productRepo.getOne(item.getProductId());
            saveOrdersItem(p, o, oItem, item.getQuantities());
            oItems.add(oItem);
        });
    }

    @Override
    public void updateOrders(List<CartItemRequest> request) throws BaseException {
        Account account = SecurityContextHelper.getCurrentAccount();
        List<Orders> ordersList = ordersRepo.findByAccount(account).stream().filter(o -> o.getStatus().equals(OrdersStatus.NEW.getCode())).collect(Collectors.toList());
        Map<Integer, List<Orders>> ordersMap = ordersRepo.findByAccount(account).stream().collect(Collectors.groupingBy(Orders::getStatus));

        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }
        // only update orders which have status = NEW
        Orders oUpdatable = ordersRepo.getOrdersUpdatable(account.getId());
        List<OrdersItem> itemsInOrd = ordersItemRepo.findByOrdersId(oUpdatable.getId());

        List<Product> productsByRequest = productRepo.getProductByRequest(request.stream().map(CartItemRequest::getProductId).collect(Collectors.toList()));
        request.forEach(r -> {

        });

        // check product in request is exist in orders item or not
        Map<Integer, Integer> cart = new HashMap<>();

        if (CollectionUtils.isEmpty(itemsInOrd)) {
            for (CartItemRequest r : request) {
                OrdersItem ordersItem = new OrdersItem();
                ordersItem.setOrder(oUpdatable);
            }
        }

        itemsInOrd.forEach(i -> {
            cart.put(i.getProduct().getId(), i.getQuantity());
            for (CartItemRequest r : request) {
                if (i.getProduct().getId().equals(r.getProductId())) {
                    i.setQuantity(r.getQuantities());
                }
            }
        });

        Set<Integer> productIdInCart = itemsInOrd.stream().map(OrdersItem::getProduct).map(Product::getId).collect(Collectors.toSet());


    }

    private void saveOrdersItem(Product product, Orders orders, OrdersItem ordersItem, Integer quantities) {
        ordersItem.setOrder(orders);
        ordersItem.setProduct(product);
        ordersItem.setSku(null);
        ordersItem.setPrice(product.getPrice());
        ordersItem.setDiscount(product.getDiscount());
        ordersItem.setQuantity(quantities);
        ordersItem.setCreatedAt(new Date());
        ordersItem.setUpdatedAt(new Date());
        ordersItem.setContent(null);
    }
}
