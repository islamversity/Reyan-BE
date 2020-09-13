package com.islamversity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class ReyanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReyanApplication.class, args);
    }

    @Bean
    CommandLineRunner demo() {
        return args -> {
        };
    }

}
