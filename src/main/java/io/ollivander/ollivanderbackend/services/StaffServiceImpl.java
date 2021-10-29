package io.ollivander.ollivanderbackend.services;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Role;
import io.ollivander.ollivanderbackend.model.repos.AccountRepository;
import io.ollivander.ollivanderbackend.utils.SecurityContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private AccountRepository accountRepo;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    @Transactional
    public List<Object> list() throws BaseException {

        Account account = SecurityContextHelper.getCurrentAccount();

        List<Account> staffs = accountRepo.findAccountByRole(Role.ROLE_STAFF);
        List<Object> simplifyStaffs = new ArrayList<>();

        for (Account s : staffs) {
            Map<String, Object> simplifyStaff = new HashMap<>();
            simplifyStaff.put("id", s.getId());
            simplifyStaff.put("displayName", s.getDisplayName());
            simplifyStaff.put("permission", s.getAccountPermission(s.getId()));

            simplifyStaffs.add(simplifyStaff);
        }
        return simplifyStaffs;
    }
}
