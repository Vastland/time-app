package org.timeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(KafkaApplication.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}