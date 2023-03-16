package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.response.MenuInfoResp;
import com.hyun.CoffeOrderingSystem.dto.response.PopularMenu;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    private List<Menu> mockMenus;

    @BeforeEach
    void setUp() {
        mockMenus = new ArrayList<>(Arrays.asList(
                new Menu("아메리카노", new BigDecimal("2500")),
                new Menu("카페라떼", new BigDecimal("4000"))
        ));
    }

    @Test()
    @DisplayName("메뉴 리스트 조회 테스트")
    void getMenuListTest() {
        /* given - 데이터 준비 */

        /* stub - 가짜 객체 행동 정의 */
        when(menuRepository.findAll()).thenReturn(mockMenus);

        /* when - 테스트 실행 */
        List<MenuInfoResp> result = menuService.getMenuList();

        /* then - 검증 */
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("아메리카노");
        assertThat(result.get(0).getPrice()).isEqualTo(new BigDecimal("2500"));
        assertThat(result.get(1).getName()).isEqualTo("카페라떼");
        assertThat(result.get(1).getPrice()).isEqualTo(new BigDecimal("4000"));
    }

    @Test
    @DisplayName("인기 메뉴 조회 테스트")
    void getPopularMenu() {
        /* given - 데이터 준비 */
        LocalDateTime date = LocalDateTime.now().minusWeeks(1);
        PopularMenu popularMenu1 = new PopularMenu(1L, "아메리카노", 3L);
        PopularMenu popularMenu2 = new PopularMenu(2L, "카페라떼", 1L);
        PopularMenu popularMenu3 = new PopularMenu(3L, "아이스 아메리카노", 5L);
        List<PopularMenu> popularMenuList = new ArrayList<>(Arrays.asList(
                popularMenu3,
                popularMenu1,
                popularMenu2
        ));

        /* stub - 가짜 객체 행동 정의 */
        when(menuRepository.findPopularMenusInLast7Days(any(LocalDateTime.class), any(PageRequest.class)))
                .thenReturn(popularMenuList);

        /* when - 테스트 실행 */
        List<PopularMenu> result = menuService.getPopularMenu();

        /* then - 검증 */
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getMenuName()).isEqualTo("아이스 아메리카노");
        assertThat(result.get(0).getOrderCount()).isEqualTo(5L);
    }
}