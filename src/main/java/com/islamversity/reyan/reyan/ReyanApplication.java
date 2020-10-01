package com.islamversity.reyan.reyan;

import com.islamversity.reyan.reyan.service.DataLoaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class ReyanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReyanApplication.class, args);
    }

    @Bean
    CommandLineRunner feeding(DataLoaderService dataLoaderService) {
        return args -> {
            dataLoaderService.loadLanguages();
            dataLoaderService.loadQuran();
            dataLoaderService.loadReciters();
        };
    }


}
