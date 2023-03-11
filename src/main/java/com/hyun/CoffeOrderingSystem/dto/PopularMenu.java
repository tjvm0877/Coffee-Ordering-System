package com.hyun.CoffeOrderingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularMenu {

	private Long menuId;
	private String menuName;
	private Long orderCount;

	public PopularMenu(Long menuId, String menuName, Long orderCount) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.orderCount = orderCount;
	}
}
