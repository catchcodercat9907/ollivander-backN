package io.ollivander.ollivanderbackend.model.repos;

import io.ollivander.ollivanderbackend.model.dto.AccountRoleObject;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Role;

import java.util.Collection;
import java.util.List;

public interface AccountRepositoryCustom {

    List<Account> findAccountByRole(Role role);

    List<AccountRoleObject> findAccountRoles(Collection<Integer> accountIds);
}