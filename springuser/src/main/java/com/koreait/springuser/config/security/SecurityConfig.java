package com.koreait.springuser.config.security;

import com.koreait.springuser.config.security.main.FormAuthenticationProvider;
import com.koreait.springuser.config.security.main.FormSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()

                .httpBasic().disable()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login_proc")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error")
                    .usernameParameter("userid")
                    .passwordParameter("userpw")
                    .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                // /css/**, /images/**, /js/** 등 정적 리소스는 보안필터를 거치지 않게 한다.
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/", "/login", "/signup");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
