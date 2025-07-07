package com.hamedTech.eccomerce.repository;

import com.hamedTech.eccomerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
