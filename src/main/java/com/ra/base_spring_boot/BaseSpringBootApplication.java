package com.ra.base_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ra.base_spring_boot")
@EntityScan(basePackages = "com.ra.base_spring_boot.model")
@EnableJpaRepositories(basePackages = "com.ra.base_spring_boot.repository")
public class BaseSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseSpringBootApplication.class, args);
        
    }
}
