package io.ollivander.ollivanderbackend.rest;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.dto.CommodityEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.CustomerCareEnablePermissionRequest;
import io.ollivander.ollivanderbackend.model.dto.ResponseObject;
import io.ollivander.ollivanderbackend.services.StaffServiceImpl;
import io.ollivander.ollivanderbackend.services.admin.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminSv;

    @Autowired
    private StaffServiceImpl staffSv;

    @RequestMapping(value = "/setting/permissions", method = RequestMethod.GET)
    @ResponseBody
    public final ResponseEntity<Object> enableTrainerFinance(@RequestParam(required = false) Integer accountId) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            response.setResponseData(adminSv.getFeature(accountId));
        } catch (BaseException e) {
            response.setError(e.getError());
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/commodity/enable", method = RequestMethod.POST)
    @ResponseBody
    public final ResponseEntity<Object> enableCommodityPermission(@RequestBody @Valid CommodityEnablePermissionRequest request) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            response.setResponseData(adminSv.enableCommodityPermission(request));
        } catch (BaseException e) {
            response.setError(e.getError());
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/customer-care/enable", method = RequestMethod.POST)
    @ResponseBody
    public final ResponseEntity<Object> enableCustomerCarePermission(@RequestBody CustomerCareEnablePermissionRequest request) {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            response.setResponseData(adminSv.enableCustomerCarePermission(request));
        } catch (BaseException e) {
            response.setError(e.getError());
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/staff/list", method = RequestMethod.GET)
    @ResponseBody
    public final ResponseEntity<Object> getListFeature() throws BaseException {
        ResponseObject<Object> response = new ResponseObject<Object>();
        try {
            response.setResponseData(staffSv.list());
        } catch (BaseException e) {
            response.setError(e.getError());
        }
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
