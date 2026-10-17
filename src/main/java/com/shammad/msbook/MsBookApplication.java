package com.shammad.msbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBookApplication.class, args);
    }

}
