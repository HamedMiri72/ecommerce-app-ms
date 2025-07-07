package com.hamedTech.eccomerce.customer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Customer {


    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
