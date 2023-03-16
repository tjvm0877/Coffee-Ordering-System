package com.hyun.CoffeOrderingSystem.entity;

import com.hyun.CoffeOrderingSystem.util.converter.BigDecimalToBigIntegerAttributeConverter;
import lombok.Getter;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "MENU")
public class Menu {
    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Convert(converter = BigDecimalToBigIntegerAttributeConverter.class)
    @Column(name = "price", columnDefinition = "BIGINT")
    private BigDecimal price;
}