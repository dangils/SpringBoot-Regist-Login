package com.koreait.springuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringuserApplication.class, args);
    }

}
