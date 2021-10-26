package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.dto.AccountRoleObject;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Role;
import io.ollivander.ollivanderbackend.utils.AppUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAccountByRole(Role role) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Account> criteriaQuery = criteriaBuilder
                .createQuery(Account.class);

        Root<Account> root = criteriaQuery.from(Account.class);

        criteriaQuery.select(root);

        Expression<Collection<Role>> roles = root.get("roles");

        criteriaQuery.where(criteriaBuilder.isMember(role, roles));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<AccountRoleObject> findAccountRoles(Collection<Integer> accountIds) {
        if (CollectionUtils.isEmpty(accountIds)) return Collections.emptyList();
        return AppUtils.queryBatch(accountIds, ids -> {
            String jpql = "SELECT new io.ollivander.ollivanderbackend.model.dto.AccountRoleObject(a.id, r) FROM Account a " +
                    "INNER JOIN a.roles r WHERE a.id IN :ids";

            TypedQuery<AccountRoleObject> query = entityManager.createQuery(jpql, AccountRoleObject.class);
            query.setParameter("ids", ids);
            return query.getResultList();
        });
    }

}
