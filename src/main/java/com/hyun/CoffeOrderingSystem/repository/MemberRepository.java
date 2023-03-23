package com.hyun.CoffeOrderingSystem.repository;


import com.hyun.CoffeOrderingSystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Member s where s.id=:id")
    Member findByIdWithPessimisticLock(@Param("id") Long id);
}
