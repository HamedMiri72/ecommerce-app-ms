package com.hamedTech.eccomerce.orderline.repository;

import com.hamedTech.eccomerce.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

}
