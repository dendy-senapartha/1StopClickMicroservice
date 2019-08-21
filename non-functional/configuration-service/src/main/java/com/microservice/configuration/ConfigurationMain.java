package com.microservice.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/*
 * Created by dendy-prtha on 20/08/2019.
 * TODO: Add a class header comment!
 */

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigurationMain {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationMain.class, args);
    }
}
