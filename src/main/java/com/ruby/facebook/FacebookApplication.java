package com.ruby.facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class, GsonAutoConfiguration.class})
public class FacebookApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);

    }

}
