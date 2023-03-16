package org.com.ehotel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @Slf4j
public class EHotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EHotelApplication.class, args);
        log.info("Application started");
    }
}