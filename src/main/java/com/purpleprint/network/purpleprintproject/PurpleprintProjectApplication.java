package com.purpleprint.network.purpleprintproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableScheduling
@EnableSwagger2
@SpringBootApplication
public class PurpleprintProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurpleprintProjectApplication.class, args);
    }

    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));

    }
}
