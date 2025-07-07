package com.hamedTech.eccomerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(


        @NotNull(message = "Product ID is required")
        Integer prodiuctId,
        @Positive(message = "Quantity must be positive")
        double quantity

) {
}
