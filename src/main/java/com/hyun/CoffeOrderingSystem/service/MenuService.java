package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.response.MenuInfoResp;
import com.hyun.CoffeOrderingSystem.dto.response.PopularMenu;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<MenuInfoResp> getMenuList() {
        List<Menu> menus = menuRepository.findAll();

        List<MenuInfoResp> menuInfoRespList = new ArrayList<>();
        for (Menu i : menus) {
            menuInfoRespList.add(MenuInfoResp.from(i));
        }

        return menuInfoRespList;
    }

    @Transactional(readOnly = true)
    public List<PopularMenu> getPopularMenu() {
        LocalDateTime dateSevenDaysAgo = LocalDateTime.now().minusWeeks(1);
        return menuRepository.findPopularMenusInLast7Days(dateSevenDaysAgo, PageRequest.of(0, 3));
    }
}
