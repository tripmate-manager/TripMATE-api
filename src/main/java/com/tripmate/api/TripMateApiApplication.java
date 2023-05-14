package com.tripmate.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.tripmate")
public class TripMateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripMateApiApplication.class, args);
    }

}
