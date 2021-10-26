package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Orders;
import io.ollivander.ollivanderbackend.model.entities.OrdersItem;
import io.ollivander.ollivanderbackend.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersItemsRepository extends JpaRepository<OrdersItem, Integer>, CrudRepository<OrdersItem, Integer> {
    List<OrdersItem> findByOrdersId(Integer ordersId);

//    Product findProductInOrders(Integer productId);
}
