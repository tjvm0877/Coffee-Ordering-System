package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.OrderCoffeeReq;
import com.hyun.CoffeOrderingSystem.entity.Member;
import com.hyun.CoffeOrderingSystem.entity.Menu;
import com.hyun.CoffeOrderingSystem.entity.Order;
import com.hyun.CoffeOrderingSystem.repository.MemberRepository;
import com.hyun.CoffeOrderingSystem.repository.MenuRepository;

import com.hyun.CoffeOrderingSystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final PointService pointService;

    @Override
    @Transactional
    public void OrderCoffee(OrderCoffeeReq request) {
        Member member = memberRepository.findById(request.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. 회원번호를 다시 확인해주세요."));
        Menu menu = menuRepository.findById(request.getMenu_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다. 메뉴를 다시 확인해주세요."));

        pointService.Payment(member, menu.getPrice());
        Order newOrder = new Order(member, menu, OffsetDateTime.now(), menu.getPrice());
        orderRepository.save(newOrder);
    }
}
