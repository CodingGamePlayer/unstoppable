package com.example.batisproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion){

    Info info = new Info()
            .title("Unstoppable Team's Rest API Document")
            .version(springdocVersion)
            .description("우지당토 Project API");

    return new OpenAPI()
            .components(new Components())
            .info(info);
    }
}
