package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.dto.CartItemResponse;
import io.ollivander.ollivanderbackend.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>, CrudRepository<CartItem, Integer> {
//    @Query("SELECT new io.ollivander.ollivanderbackend.model.dto.CartItemResponse() FROM CartItem ci " +
//            "INNER JOIN Cart c ON ci.cart = c")
//    CartItemResponse getAccountCartItem(Integer accountId);
}
