package com.hyun.CoffeOrderingSystem.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hyun.CoffeOrderingSystem.util.converter.BigDecimalDeserializer;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PointChargeReq {

    private Long id;

    @JsonDeserialize(using = BigDecimalDeserializer.class)
    private BigDecimal amount;
}
