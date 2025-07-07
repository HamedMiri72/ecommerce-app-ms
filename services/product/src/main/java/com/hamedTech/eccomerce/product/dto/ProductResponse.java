package com.hamedTech.eccomerce.product.dto;

import java.math.BigDecimal;

public record ProductResponse(

        String id,
        String name,
        String description,
        BigDecimal price,
        double availableQuantity,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
