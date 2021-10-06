package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, CrudRepository<Orders, Integer> {
    Orders findByAccount(Account account);
}
