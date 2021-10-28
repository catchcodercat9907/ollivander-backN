package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, AccountRepositoryCustom {
    Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);

    Account findFirstByUsername(String username);
}
