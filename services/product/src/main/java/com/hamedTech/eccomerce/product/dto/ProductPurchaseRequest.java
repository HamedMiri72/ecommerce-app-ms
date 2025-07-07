package com.hamedTech.eccomerce.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductPurchaseRequest(

        @NotNull(message = "Product ID is required")
        Integer productId,
        @NotNull(message = "Product price is required")
        @Positive(message = "Product quantity is required")
        double quantity


) {
}
