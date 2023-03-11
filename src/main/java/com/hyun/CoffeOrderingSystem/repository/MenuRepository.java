package com.hyun.CoffeOrderingSystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.hyun.CoffeOrderingSystem.dto.PopularMenu;
import com.hyun.CoffeOrderingSystem.entity.Menu;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	@Query("SELECT new com.hyun.CoffeOrderingSystem.dto.PopularMenu(m.id, m.name, COUNT(o)) " +
		"FROM Order o JOIN Menu m ON o.menuId = m.id " +
		"WHERE o.orderDateTime >= :dateSevenDaysAgo " +
		"GROUP BY m.id, m.name " +
		"ORDER BY COUNT(o) DESC")
	List<PopularMenu> findPopularMenusInLast7Days(@Param("dateSevenDaysAgo") LocalDateTime dateSevenDaysAgo, Pageable pageable);
}
