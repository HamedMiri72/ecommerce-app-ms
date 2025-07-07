package com.hamedTech.eccomerce.service;

import com.hamedTech.eccomerce.customer.CustomerClient;
import com.hamedTech.eccomerce.dto.OrderRequest;
import com.hamedTech.eccomerce.dto.PurchaseRequest;
import com.hamedTech.eccomerce.entity.OrderLineRequest;
import com.hamedTech.eccomerce.exception.BusinessException;
import com.hamedTech.eccomerce.kafka.OrderConfirmation;
import com.hamedTech.eccomerce.kafka.OrderProducer;
import com.hamedTech.eccomerce.mapper.OrderMapper;
import com.hamedTech.eccomerce.orderline.service.OrderLineService;
import com.hamedTech.eccomerce.product.ProductClient;
import com.hamedTech.eccomerce.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public String createOrder(@Valid OrderRequest request) {
        // check the customer with openFeign

        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException(format("Customer not found by provided ID:: %s", request.customerId())));

        // check the products with openFeign

        var purchasedProducts = productService.purchaseProducts(request.products())
                .orElseThrow(() -> new BusinessException(format("Product not found by provided ID:: %s", request.customerId())));
        var order = orderRepository.save(orderMapper.toOrder(request));

        // persist the order lines
        for(PurchaseRequest purchaseRequest : request.products()) {

            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.prodiuctId(),
                            purchaseRequest.quantity()
                    )
            );


        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId().toString();
    }
}
