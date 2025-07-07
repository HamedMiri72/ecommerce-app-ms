package com.hamedTech.eccomerce.customer;


import com.hamedTech.eccomerce.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    @GetMapping("/findById/{customerId}")
    Optional<CustomerResponse> findCustomerById(@PathVariable String customerId);
}
