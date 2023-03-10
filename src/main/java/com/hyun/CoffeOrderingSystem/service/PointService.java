package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.PointChargeReq;
import com.hyun.CoffeOrderingSystem.entity.Member;
import com.hyun.CoffeOrderingSystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PointService {

    private final MemberRepository memberRepository;

    @Transactional
    public void PointCharge(PointChargeReq request) {
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. 회원번호를 확인하시고 다시 시도해주세요."));
        member.pointCharge(request.getAmount());
    }

    @Transactional
    public void Payment(Member member, BigDecimal amount) {
        if (!member.payable(amount)) {
            throw new IllegalArgumentException("포인트 잔액이 부족합니다. 포인트를 충전하고 다시 주문 해주세요.");
        }

        member.pointCharge(amount);
    }
}
