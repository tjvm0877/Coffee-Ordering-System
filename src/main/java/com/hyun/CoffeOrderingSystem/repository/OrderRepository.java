package com.hyun.CoffeOrderingSystem.repository;

import com.hyun.CoffeOrderingSystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
