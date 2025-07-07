package com.hamedTech.eccomerce.mapper;

import com.hamedTech.eccomerce.entity.Order;
import com.hamedTech.eccomerce.entity.OrderLine;
import com.hamedTech.eccomerce.entity.OrderLineRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {


        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();

    }
}
