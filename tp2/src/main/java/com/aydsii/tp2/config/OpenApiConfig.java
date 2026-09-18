package com.aydsii.tp2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI techStoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TechStore API")
                        .description("API REST de gestion de una tienda de tecnologia")
                        .version("1.0.0"));
    }
}