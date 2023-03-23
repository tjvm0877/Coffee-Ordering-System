package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.request.OrderCoffeeReq;
import com.hyun.CoffeOrderingSystem.entity.Member;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.repository.MemberRepository;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;
import com.hyun.CoffeOrderingSystem.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceConcurrencyControlTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    public void insert() {
        Menu menu = new Menu("아메리카노", new BigDecimal(2500));
        menuRepository.save(menu);

        Member member = new Member("user", "010-1234-1234", new BigDecimal(10000));
        memberRepository.save(member);
    }

    @AfterEach
    public void delete() {
        menuRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("커피 주문")
    public void orderTest() {
        // given
        Member member = memberRepository.findAll().get(0);
        Menu menu = menuRepository.findAll().get(0);
        OrderCoffeeReq request = new OrderCoffeeReq(member.getId(), menu.getId());

        // when
        orderService.OrderCoffee(request);

        // then
        Member result = memberRepository.findAll().get(0);
        assertThat(result.getPoint()).isEqualTo(new BigDecimal(7500));
    }

    @Test
    @DisplayName("동시에 2개 주문")
    public void orderAtSameTime() {
        // given
        Member member = memberRepository.findAll().get(0);
        Menu menu = menuRepository.findAll().get(0);
        OrderCoffeeReq request = new OrderCoffeeReq(member.getId(), menu.getId());

        // when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> orderService.OrderCoffee(request)),
                CompletableFuture.runAsync(() -> orderService.OrderCoffee(request))
        ).join();

        // then
        Member result = memberRepository.findAll().get(0);
        assertThat(result.getPoint()).isEqualTo(new BigDecimal(5000));
    }
}
