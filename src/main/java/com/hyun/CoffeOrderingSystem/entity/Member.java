package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

import lombok.Getter;

@Entity
@Table(name = "MEMBERS")
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private Long point;


    public void pointCharge(BigDecimal amount) {
        BigDecimal pointBD = new BigDecimal(this.point);
        pointBD.add(amount);
    }

    public boolean payable(BigDecimal amount) {
        BigDecimal pointBD = new BigDecimal(this.point);
        if (pointBD.compareTo(amount) < 0) {
            return false;
        }

        return true;
    }
}
