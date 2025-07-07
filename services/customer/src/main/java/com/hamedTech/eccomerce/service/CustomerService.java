package com.hamedTech.eccomerce.service;


import com.hamedTech.eccomerce.customer.Customer;
import com.hamedTech.eccomerce.dto.CustomerRequest;
import com.hamedTech.eccomerce.dto.CustomerResponse;
import com.hamedTech.eccomerce.exception.CustomerNotFoundException;
import com.hamedTech.eccomerce.mapper.CustomerMapper;
import com.hamedTech.eccomerce.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String createCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.save(customerMapper.customerDtoToCustomer(request));

        return customer.getId();

    }


    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(format("Can not update customer:: No Customer found with provided ID:: %S", request.id())));


        mergerCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponse> responses = customers.stream().map(customer -> customerMapper.ToCustomerResponse(customer))
                .collect(Collectors.toList());

        return responses;
    }

    public Boolean existsById(String customerId) {

        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(format("Customer not found by provided ID:: %s", customerId)));

        CustomerResponse response = customerMapper.ToCustomerResponse(customer);
        return response;
    }

    public void deleteCustomer(String customerId) {

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(toString().formatted("Customer not Found by provided Id:: %s", customerId)));

        customerRepository.delete(customer);

    }
}
