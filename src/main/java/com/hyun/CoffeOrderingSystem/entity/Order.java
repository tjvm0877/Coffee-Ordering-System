package com.hyun.CoffeOrderingSystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @Column(name = "ORDER_DATE")
    private OffsetDateTime orderDateTime;

    @Column(name = "ORDER_PRICE")
    private BigDecimal price;

    public Order(Member member, Menu menu, OffsetDateTime orderDateTime, BigDecimal price) {
        this.member = member;
        this.menu = menu;
        this.orderDateTime = orderDateTime;
        this.price = price;
    }
}
