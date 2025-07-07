package com.hamedTech.eccomerce.dto;

import com.hamedTech.eccomerce.customer.Address;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
){
}
