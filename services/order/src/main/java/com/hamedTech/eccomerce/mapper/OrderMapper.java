package com.hamedTech.eccomerce.mapper;

import com.hamedTech.eccomerce.dto.OrderRequest;
import com.hamedTech.eccomerce.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequest request) {

        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }
}
