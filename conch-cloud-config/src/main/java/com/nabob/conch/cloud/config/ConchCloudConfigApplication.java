package com.nabob.conch.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConchCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConchCloudConfigApplication.class, args);
    }

}
