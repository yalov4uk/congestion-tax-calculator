package com.yalauchuk.http;

import com.yalauchuk.calculator.CongestionTaxCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CongestionTaxCalculator congestionTaxCalculator() {
        return new CongestionTaxCalculator();
    }
}
