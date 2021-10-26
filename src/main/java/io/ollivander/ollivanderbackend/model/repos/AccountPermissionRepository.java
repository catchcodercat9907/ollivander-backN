package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.AccountPermission;
import io.ollivander.ollivanderbackend.model.entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AccountPermissionRepository extends JpaRepository<AccountPermission, Integer>,
        JpaSpecificationExecutor<AccountPermission>, AccountPermissionRepositoryCustom {

    List<AccountPermission> findByAccount_IdAndAccount(Integer accountId, Account account);

    List<AccountPermission> findByAccount_IdAndFeature(Integer accountId, Feature feature);

    @Query("SELECT new AccountPermission(p.id, p.account.id, p.feature.id, p.feature.featureKey, p.permission) FROM AccountPermission p WHERE p.account.id IN (?1)")
    List<AccountPermission> findCompactByAccountIds(Collection<Integer> accountIds);
}
