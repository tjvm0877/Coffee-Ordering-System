package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.request.OrderCoffeeReq;
import com.hyun.CoffeOrderingSystem.entity.Member;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.entity.Order;
import com.hyun.CoffeOrderingSystem.repository.MemberRepository;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;

import com.hyun.CoffeOrderingSystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void OrderCoffee(OrderCoffeeReq request) {
        Member member = memberRepository.findByIdWithPessimisticLock(request.getUser_id());
        Menu menu = menuRepository.findById(request.getMenu_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다. 메뉴를 다시 확인해주세요."));

        BigDecimal amount = menu.getPrice();
        if (!member.payable(amount)) {
            throw new IllegalArgumentException("포인트 잔액이 부족합니다. 포인트를 충전하고 다시 주문 해주세요.");
        }
        member.payment(amount);

        Order newOrder = new Order(member.getId(), menu.getId(), LocalDateTime.now(), menu.getPrice());
        orderRepository.save(newOrder);
    }
}
