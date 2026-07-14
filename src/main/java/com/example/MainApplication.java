
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example"})
@EnableMongoRepositories(basePackages = "com.example.repo")
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
}
