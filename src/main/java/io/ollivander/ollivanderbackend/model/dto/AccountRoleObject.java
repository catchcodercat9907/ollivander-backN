package io.ollivander.ollivanderbackend.model.dto;

import io.ollivander.ollivanderbackend.model.entities.Role;

public class AccountRoleObject {
    private final Integer accountId;
    private final Role role;

    public AccountRoleObject(Integer accountId, Role role) {
        this.accountId = accountId;
        this.role = role;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Role getRole() {
        return role;
    }
}
