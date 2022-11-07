package com.purpleprint.network.purpleprintproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PurpleprintProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurpleprintProjectApplication.class, args);
    }

}
