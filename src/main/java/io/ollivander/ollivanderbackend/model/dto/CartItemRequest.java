package io.ollivander.ollivanderbackend.model.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CartItemRequest {
    @NotNull
    private Integer productIds;
    @Min(1)
    private Integer quantities;

}
