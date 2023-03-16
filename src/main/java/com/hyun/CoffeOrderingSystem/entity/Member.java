package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.*;

import java.math.BigDecimal;

import com.hyun.CoffeOrderingSystem.util.converter.BigDecimalToBigIntegerAttributeConverter;
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

    @Convert(converter = BigDecimalToBigIntegerAttributeConverter.class)
    @Column(name = "point", columnDefinition = "BIGINT")
    private BigDecimal point;


    public void pointCharge(BigDecimal amount) {
        this.point.subtract(amount);
    }

    public boolean payable(BigDecimal amount) {
        if (this.point.compareTo(amount) < 0) {
            return false;
        }
        return true;
    }

    public void payment(BigDecimal amount) {
        this.point.subtract(amount);
    }
}
