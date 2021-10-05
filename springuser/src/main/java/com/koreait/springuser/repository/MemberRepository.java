package com.koreait.springuser.repository;

import com.koreait.springuser.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserid(String username);
}
