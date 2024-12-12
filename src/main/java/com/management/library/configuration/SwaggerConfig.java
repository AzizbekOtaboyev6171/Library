package com.management.library.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Library Management System API")
                        .description("This is a sample Spring Boot RESTful service leveraging springdoc-openapi with OpenAPI 3 for API documentation")
                        .version("v1.0"))
                .addTagsItem(new Tag().name("Auth").description("Methods for authentication"))
                .addTagsItem(new Tag().name("Role").description("Methods for Role"))
                .addTagsItem(new Tag().name("User").description("Methods for User"))
                .addTagsItem(new Tag().name("Genre").description("Methods for Genre"))
                .addTagsItem(new Tag().name("Author").description("Methods for Author"))
                .addTagsItem(new Tag().name("Member").description("Methods for Member"))
                .addTagsItem(new Tag().name("Book").description("Methods for Book"))
                .addTagsItem(new Tag().name("Loan").description("Methods for Loan"));
    }
}