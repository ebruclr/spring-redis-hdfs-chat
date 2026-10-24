package com.example.project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class BigDataProject2Application {


    public static void main(String[] args) {
        SpringApplication.run(BigDataProject2Application.class, args);
    }

}
