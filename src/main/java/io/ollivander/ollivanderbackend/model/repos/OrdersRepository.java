package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Orders;
import io.ollivander.ollivanderbackend.model.enums.OrdersStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, CrudRepository<Orders, Integer> {
    List<Orders> findByAccount(Account account);

    List<Orders> findByAccountIdAndStatus(Integer accountId, Integer status);

    @Query("SELECT o FROM Orders o WHERE o.account.id = ?1 AND o.status = 1")   // o.status = 1 ~ OrdersStatus = NEW
    Orders getOrdersUpdatable(Integer accountId);
}
