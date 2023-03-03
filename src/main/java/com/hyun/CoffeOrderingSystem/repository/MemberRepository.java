package com.hyun.CoffeOrderingSystem.repository;


import com.hyun.CoffeOrderingSystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
