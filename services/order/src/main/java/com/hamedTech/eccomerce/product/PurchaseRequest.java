package com.hamedTech.eccomerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(


        @NotNull(message = "Product ID is required")
        Integer prodiuctiD,
        @Positive(message = "Quantity must be positive")
        double quantity

) {
}
