package com.hyun.CoffeOrderingSystem.repository;

import com.hyun.CoffeOrderingSystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
