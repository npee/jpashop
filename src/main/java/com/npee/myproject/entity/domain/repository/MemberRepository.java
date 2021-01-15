package com.npee.myproject.entity.domain.repository;

import com.npee.myproject.entity.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // findAll, save는 이미 만들어져 있다.
    List<Member> findByName(String name);
}
