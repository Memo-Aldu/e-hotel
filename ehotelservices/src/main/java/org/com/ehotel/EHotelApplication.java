package org.com.ehotel;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Data
public class EHotelApplication {
    public static void main(String[] args) {
        SpringApplication.run(EHotelApplication.class, args);
    }
}