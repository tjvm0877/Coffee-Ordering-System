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
public class PointServiceImpl implements PointService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void PointCharge(PointChargeReq request) {
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. 회원번호를 확인하시고 다시 시도해주세요."));
        member.pointCharge(request.getAmount());
    }

    @Override
    @Transactional
    public void Payment(BigDecimal amount) {

    }
}
