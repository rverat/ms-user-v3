package com.onbank.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class MsUserV3Application {

    public static void main(String[] args) {
        SpringApplication.run(MsUserV3Application.class, args);
    }

}
