package com.hyun.CoffeOrderingSystem.service;

import com.hyun.CoffeOrderingSystem.dto.PointChargeReq;

import java.math.BigDecimal;

public interface PointService {
    public void PointCharge(PointChargeReq request);

    public void Payment(BigDecimal amount);

}
