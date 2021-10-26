package io.ollivander.ollivanderbackend.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class CustomerCareEnablePermissionRequest {
    @NotNull
    private Integer accountId;

    private Boolean enableCustomerCare;
}
