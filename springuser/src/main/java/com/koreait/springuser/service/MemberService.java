package com.koreait.springuser.service;

import com.koreait.springuser.data.MemberDTO;
import com.koreait.springuser.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(MemberDTO memberDTO){
        System.out.println(memberDTO.getUserpw());
        System.out.println(passwordEncoder.encode(memberDTO.getUserpw()));
        memberDTO.setUserpw(passwordEncoder.encode(memberDTO.getUserpw()));
        return memberRepository.save(memberDTO.toEntity()).getId();
    }
}
