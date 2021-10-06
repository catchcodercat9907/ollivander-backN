package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.dto.CartResponse;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends JpaRepository<Cart, Integer>, CrudRepository<Cart, Integer> {
    Cart findByAccount(Account account);

    @Query("SELECT new io.ollivander.ollivanderbackend.model.dto.CartResponse(c.id, c.account.id, c.sessionId, c.token, c.status, c.createdAt, c.updatedAt, c.content) FROM Cart c ")
    CartResponse getAccountCart(Integer accountId);
}
