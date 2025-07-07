package com.hamedTech.eccomerce.mapper;

import com.hamedTech.eccomerce.customer.Customer;
import com.hamedTech.eccomerce.dto.CustomerRequest;
import com.hamedTech.eccomerce.dto.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer customerDtoToCustomer(CustomerRequest request) {

        return Customer.builder()
                .id(request.id())
                .address(request.address())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }

    public CustomerResponse ToCustomerResponse(Customer customer) {

       return new CustomerResponse(
               customer.getId(),
               customer.getFirstName(),
               customer.getLastName(),
               customer.getEmail(),
               customer.getAddress()
       );

    }
}
