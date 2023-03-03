package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.MenuInfoResp;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuInfoResp> getMenuList() {
        List<Menu> menus = menuRepository.findAll();

        List<MenuInfoResp> menuInfoRespList = new ArrayList<>();
        for (Menu i : menus) {
            menuInfoRespList.add(MenuInfoResp.from(i));
        }

        return menuInfoRespList;
    }
}
