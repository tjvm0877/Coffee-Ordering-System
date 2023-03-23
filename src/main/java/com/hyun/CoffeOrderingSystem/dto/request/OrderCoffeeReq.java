package com.hyun.CoffeOrderingSystem.dto.request;

import lombok.Getter;

@Getter
public class OrderCoffeeReq {
    private Long user_id;
    private Long menu_id;

    public OrderCoffeeReq(Long user_id, Long menu_id) {
        this.user_id = user_id;
        this.menu_id = menu_id;
    }
}
