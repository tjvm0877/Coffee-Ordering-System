package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.MenuInfoResp;

import java.util.List;

public interface MenuService {
    List<MenuInfoResp> getMenuList();
}
