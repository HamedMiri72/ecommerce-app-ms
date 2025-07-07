package com.hamedTech.eccomerce.kafka;

import com.hamedTech.eccomerce.dto.CustomerResponse;
import com.hamedTech.eccomerce.dto.ProductPurchaseResponse;
import com.hamedTech.eccomerce.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}
