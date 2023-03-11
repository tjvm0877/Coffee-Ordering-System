package com.hyun.CoffeOrderingSystem.dto;

import com.hyun.CoffeOrderingSystem.entity.Menu;

import java.math.BigDecimal;

public class MenuInfoResp {
    private Long menuId;
    private String name;
    private Long price;

    private MenuInfoResp(Long menuId, String name, Long price) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }

    public static MenuInfoResp from(Menu menu) {
        return new MenuInfoResp(menu.getId(), menu.getName(), menu.getPrice());
    }
}
