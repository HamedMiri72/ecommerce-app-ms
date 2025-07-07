package com.hamedTech.eccomerce.service;

import com.hamedTech.eccomerce.customer.CustomerClient;
import com.hamedTech.eccomerce.dto.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    public String createOrder(@Valid OrderRequest request) {
        // check the customer with openFeign

        var customer =
    }
}
