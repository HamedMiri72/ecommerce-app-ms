package com.hamedTech.eccomerce.repository;

import com.hamedTech.eccomerce.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
