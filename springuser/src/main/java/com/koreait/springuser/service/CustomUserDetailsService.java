package com.koreait.springuser.service;

import com.koreait.springuser.entity.MemberContext;
import com.koreait.springuser.entity.Member;
import com.koreait.springuser.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //전달 받은 값이 존재함
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserid(userid)
                .orElseThrow( () -> new UsernameNotFoundException("userid를 찾을 수 없습니다 : " + userid));
        return new MemberContext(member);
    }
}


