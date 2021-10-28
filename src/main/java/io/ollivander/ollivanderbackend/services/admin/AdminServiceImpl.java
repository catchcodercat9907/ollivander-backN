package io.ollivander.ollivanderbackend.services.admin;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.model.DbConst;
import io.ollivander.ollivanderbackend.model.dto.CommodityEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.CustomerCareEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.PermissionOptions;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.AccountPermission;
import io.ollivander.ollivanderbackend.model.entities.Feature;
import io.ollivander.ollivanderbackend.model.repos.AccountPermissionRepository;
import io.ollivander.ollivanderbackend.model.repos.AccountRepository;
import io.ollivander.ollivanderbackend.model.repos.FeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private FeatureRepository featureRepo;

    @Autowired
    private AccountPermissionRepository permissionRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Override
    public Map<Object, Object> getFeature(Integer accountId) throws BaseException {
        Account account = null;
        if (accountId != null) {
            account = accountRepo.getOne(accountId);
        }
        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }

        Feature commodityFeature = featureRepo.findByFeatureKey(Feature.COMMODITY_KEY);
        List<AccountPermission> commodityPermissions = permissionRepo.findByAccount_IdAndFeature(account.getId(), commodityFeature);

        Feature customerCareFeature = featureRepo.findByFeatureKey(Feature.CUSTOMER_CARE_KEY);
        List<AccountPermission> customerCarePermissions = permissionRepo.findByAccount_IdAndFeature(account.getId(), customerCareFeature);

        // Customize permission response
        List<Map<Object, Object>> permissionResponse = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(commodityPermissions)) {
            commodityPermissions.forEach(item -> {
                Map<Object, Object> itemResponse = new HashMap<>();
                itemResponse.put(DbConst.FEATURE, item.getFeature());
                itemResponse.put(DbConst.PERMISSION, item.getPermission());
                permissionResponse.add(itemResponse);
            });
        }

        if (CollectionUtils.isNotEmpty(customerCarePermissions)) {
            customerCarePermissions.forEach(item -> {
                Map<Object, Object> itemResponse = new HashMap<>();
                itemResponse.put(DbConst.FEATURE, item.getFeature());
                itemResponse.put(DbConst.PERMISSION, item.getPermission());
                permissionResponse.add(itemResponse);
            });
        }
        Map<Object, Object> response = new HashMap<>();
        response.put(DbConst.PERMISSIONS, permissionResponse);

        return response;
    }

    @Override
    public boolean updateStaffPermission(Account account, List<PermissionOptions> rqPermissions, String position, Integer staffId) {
        // store current feature and permission (by current account) to check
        // data is changed later
        Set<AccountPermission> grantedPermissions = account.getAccountPermission(account.getId());
        Map<Integer, Integer> originPermission = new HashMap<>();
        if (!CollectionUtils.isEmpty(grantedPermissions)) {
            grantedPermissions.forEach(g -> {
                originPermission.put(g.getFeature().getId(), g.getPermission());
            });
        }

        if (account == null || CollectionUtils.isEmpty(rqPermissions)) {
            return false;
        }

        Set<Integer> requestFeatures = rqPermissions.stream().map(PermissionOptions::getFeatureId)
                .collect(Collectors.toSet());


        Map<Integer, Integer> workplacePermissionMap = new HashMap<>();
        // add or update existing permissions
        for (PermissionOptions selectedPermission : rqPermissions) {
            // check feature exists
            Integer featureId = selectedPermission.getFeatureId();
            Feature feature = featureRepo.getOne(featureId != null ? featureId : 0);
            if (feature == null) {
                continue;
            }

        }

        // compare final permissions with original permission to determine is
        // data changed
        boolean updated = false;

        for (AccountPermission ap : account.getAccountPermission()) {
            Integer featureId = ap.getFeature().getId();
            Integer permission = ap.getPermission();

            // if add new feature
            if (!originPermission.containsKey(featureId) || (originPermission.containsKey(featureId)
                    && !originPermission.get(featureId).equals(permission))) {
                updated = true;
                break;
            }
        }

        accountRepo.save(account);

        return updated;
    }

    @Override
    public boolean enableCommodityPermission(CommodityEnablePermissionRequest request) throws BaseException {
        Account account = accountRepo.getOne(request.getAccountId());
        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }
        boolean isEnable = Optional.ofNullable(request.getEnableCommodity()).orElse(false);

        updatePermision(account, isEnable, Feature.COMMODITY_KEY);
        return true;
    }

    @Override
    public boolean enableCustomerCarePermission(CustomerCareEnablePermissionRequest request) throws BaseException {
        Account account = accountRepo.getOne(request.getAccountId());
        if (account == null) {
            throw new BaseException(ErrorInfo.ACCOUNT_NOT_FOUND);
        }
        boolean isEnable = Optional.ofNullable(request.getEnableCustomerCare()).orElse(false);

        updatePermision(account, isEnable, Feature.CUSTOMER_CARE_KEY);
        return true;
    }

    private void updatePermision(Account account, boolean isEnable, String featureKey) throws BaseException {
        Feature feature = featureRepo.findByFeatureKey(featureKey);
        if (feature == null) {
            throw new BaseException(ErrorInfo.PRODUCT_NOT_FOUND);
        }
        List<AccountPermission> permissions = permissionRepo.findByAccount_IdAndFeature(account.getId(), feature)
                .stream()
                .filter(permission -> featureKey.equalsIgnoreCase(permission.getFeature().getFeatureKey()))
                .collect(Collectors.toList());

        final Integer permissionValue = isEnable ? AccountPermission.FULL_PERMISSION : AccountPermission.ASSIGNABLE;
        if (CollectionUtils.isNotEmpty(permissions)) {
            logger.info("Grant account permission: {},    ====================", account.getId());

            permissions.forEach(permission -> {
                logger.info("Update permission id: {},    ====================", permission.getId());
                permission.setPermission(permissionValue);
            });
            permissionRepo.saveAll(permissions);
        } else {
            AccountPermission newPermission = new AccountPermission(account, feature, permissionValue);
            permissionRepo.save(newPermission);
        }
    }
}
