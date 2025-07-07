package com.hamedTech.eccomerce.entity;


import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer productId;
    
    private double quantity;

}
