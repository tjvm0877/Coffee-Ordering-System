package com.hyun.CoffeOrderingSystem.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PointChargeReq {
    private Long id;
    private BigDecimal amount;
}
