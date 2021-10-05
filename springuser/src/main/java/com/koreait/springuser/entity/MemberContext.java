package com.koreait.springuser.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class MemberContext extends User{
    private Member member;

    public MemberContext(Member member) {
        super(member.getUserid(), member.getUserpw(), getAuthorities(member.getRole()));
        this.member = member;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(MemberRole role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getKey()));
    }
}
