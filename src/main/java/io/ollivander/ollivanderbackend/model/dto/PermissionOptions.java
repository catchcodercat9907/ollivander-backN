package io.ollivander.ollivanderbackend.model.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PermissionOptions {
    @Min(1)
    private Integer featureId;

    @Min(1)
    @Max(15)
    private Integer permission;

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public PermissionOptions() {
    }

    public PermissionOptions(Integer featureId, Integer permission) {
        this.featureId = featureId;
        this.permission = permission;
    }
}
