package com.coachbar.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI libraryOpenAPI() {
		return new OpenAPI().info(
				new Info().title("Library Management System API").description("API for managing books").version("1.0"));
	}
}
