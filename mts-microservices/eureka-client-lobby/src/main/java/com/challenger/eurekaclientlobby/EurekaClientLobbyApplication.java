package com.challenger.eurekaclientlobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientLobbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientLobbyApplication.class, args);
    }

}
