package com.example.cqrs_premium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CqrsPremiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqrsPremiumApplication.class, args);
    }

}
