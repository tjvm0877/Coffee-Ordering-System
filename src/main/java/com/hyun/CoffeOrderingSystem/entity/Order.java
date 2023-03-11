package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

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

    @Column(name = "order_price")
    private Long price;

    public Order(Long member, Long menu, LocalDateTime orderDateTime, Long price) {
        this.memberId = member;
        this.menuId = menu;
        this.orderDateTime = orderDateTime;
        this.price = price;
    }
}
