package com.microservice.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

/*
 * Created by dendy-prtha on 07/01/2020.
 * TODO: Add a class header comment!
 */

@Configuration
public class JacksonConfiguration {

    @Bean
    ProblemModule problemModule() {
        return new ProblemModule().withStackTraces(false);
    }
}
