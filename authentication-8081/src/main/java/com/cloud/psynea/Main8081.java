package com.cloud.psynea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.cloud.psynea.repository"})
public class Main8081 {

    public static void main(String[] args) {
        SpringApplication.run(Main8081.class, args);
    }

}
