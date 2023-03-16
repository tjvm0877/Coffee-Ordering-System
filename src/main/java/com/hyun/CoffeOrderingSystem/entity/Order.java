package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.hyun.CoffeOrderingSystem.util.converter.BigDecimalToBigIntegerAttributeConverter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long menuId;

    @Column(name = "order_date")
    private LocalDateTime orderDateTime;

    @Convert(converter = BigDecimalToBigIntegerAttributeConverter.class)
    @Column(name = "order_price", columnDefinition = "BIGINT")
    private BigDecimal price;

    public Order(Long member, Long menu, LocalDateTime orderDateTime, BigDecimal price) {
        this.memberId = member;
        this.menuId = menu;
        this.orderDateTime = orderDateTime;
        this.price = price;
    }
}
