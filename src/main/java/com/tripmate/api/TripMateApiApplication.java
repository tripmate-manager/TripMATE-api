package com.tripmate.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = "com.tripmate")
public class TripMateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripMateApiApplication.class, args);
    }

}
