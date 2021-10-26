package io.ollivander.ollivanderbackend.model.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class CommodityEnablePermissionRequest {
    @NotNull
    private Integer accountId;

    private Boolean enableCommodity;
}
