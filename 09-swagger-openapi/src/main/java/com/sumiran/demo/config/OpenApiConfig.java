package com.sumiran.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .version("1.0")
                        .description("REST API for managing students")
                        .contact(new Contact()
                                .name("Sumiran Paparkar")
                                .email("sumiranpaparkar@gmail.com")
                        )
                );
    }
}