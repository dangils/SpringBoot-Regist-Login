package com.koreait.springuser.config.security.main;

import com.koreait.springuser.entity.MemberContext;
import com.koreait.springuser.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


public class FormAuthenticationProvider implements AuthenticationProvider {
    private CustomUserDetailsService memberDetailService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userid = authentication.getName();
        String userpw = (String)authentication.getCredentials();

        MemberContext memberContext = (MemberContext) memberDetailService.loadUserByUsername(userid);
        String password = memberContext.getMember().getUserpw();

        if(!passwordEncoder.matches(userpw, password)){
            throw new BadCredentialsException("비밀번호가 틀립니다");
        }
        return new UsernamePasswordAuthenticationToken(memberContext.getMember(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
