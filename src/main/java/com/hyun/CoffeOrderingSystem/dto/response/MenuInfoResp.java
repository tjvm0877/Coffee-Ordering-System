package com.hyun.CoffeOrderingSystem.dto.response;

import com.hyun.CoffeOrderingSystem.entity.Menu;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MenuInfoResp {
    private Long menuId;
    private String name;
    private BigDecimal price;

    private MenuInfoResp(Long menuId, String name, BigDecimal price) {
        this.menuId = menuId;
        this.name = name;
        this.price = price;
    }

    public static MenuInfoResp from(Menu menu) {
        return new MenuInfoResp(menu.getId(), menu.getName(), menu.getPrice());
    }
}
