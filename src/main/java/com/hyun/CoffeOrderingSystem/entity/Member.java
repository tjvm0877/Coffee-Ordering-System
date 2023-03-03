package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "POINT")
    private BigDecimal point;
}
