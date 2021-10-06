package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrdersItemsRepository extends JpaRepository<OrdersItem, Integer>, CrudRepository<OrdersItem, Integer> {
}
