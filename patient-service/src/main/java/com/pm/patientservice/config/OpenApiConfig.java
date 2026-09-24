package com.pm.patientservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI patientServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Service API")
                        .description("REST API for managing patient data in the Patient Management microservices ecosystem.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Engineering Team")
                                .email("dev@company.com")));
    }
}