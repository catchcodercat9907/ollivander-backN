package io.ollivander.ollivanderbackend.model.dto;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CartItemRequest {
    @NotNull
    private Integer productId;
    @Min(1)
    private Integer quantities;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantities() {
        return quantities;
    }

    public void setQuantities(Integer quantities) {
        this.quantities = quantities;
    }
}
