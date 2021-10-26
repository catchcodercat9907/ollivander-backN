package io.ollivander.ollivanderbackend.services.admin;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.dto.CommodityEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.CustomerCareEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.PermissionOptions;
import io.ollivander.ollivanderbackend.model.entities.Account;

import java.util.List;
import java.util.Map;

public interface AdminService {
    boolean enableCommodityPermission(CommodityEnablePermissionRequest request) throws BaseException;

    boolean enableCustomerCarePermission(CustomerCareEnablePermissionRequest request) throws BaseException;

    Map<Object, Object> getFeature(Integer accountId) throws BaseException;

    boolean updateStaffPermission(Account account, List<PermissionOptions> rqPermissions,
                                  String position, Integer staffId);
}
