package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.request.OrderCoffeeReq;
import com.hyun.CoffeOrderingSystem.dto.response.MenuInfoResp;
import com.hyun.CoffeOrderingSystem.entity.Member;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.entity.Order;
import com.hyun.CoffeOrderingSystem.repository.MemberRepository;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;
import com.hyun.CoffeOrderingSystem.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("커피 주문 테스트")
    void orderCoffeeTest() {
        /* given - 데이터 준비 */
        OrderCoffeeReq orderCoffeeReq = new OrderCoffeeReq(1L, 1L);
        Menu menu = new Menu("아메리카노", new BigDecimal("2500"));
        Member member = new Member("user", "010-1234-1234", new BigDecimal("10000"));
        Order order = new Order(1L, 1L, LocalDateTime.now(), menu.getPrice());

        /* stub - 가짜 객체 행동 정의 */
        when(menuRepository.findById(any())).thenReturn(Optional.of(menu));
        when(memberRepository.findByIdWithPessimisticLock(any())).thenReturn(member);
        when(orderRepository.save(any())).thenReturn(order);

        /* when - 테스트 실행 */
        orderService.OrderCoffee(orderCoffeeReq);

        /* then - 검증 */
        assertThat(member.getPoint()).isEqualTo(new BigDecimal(7500));
    }
}