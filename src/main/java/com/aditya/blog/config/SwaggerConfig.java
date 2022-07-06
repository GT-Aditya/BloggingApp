package com.aditya.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getAppInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(getApiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private List<SecurityContext> securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(references()).build());
	}
	
	private List<SecurityReference> references() {
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}

	private ApiKey getApiKeys() {
		return new ApiKey("JWT", AppConstants.AUTHORIZATION_HEADER, "header");
	}

	private ApiInfo getAppInfo() {
		return new ApiInfo(
				//Application title
				"Blogging Application", 
				//Description
				"This project is developed by Aditya", 
				//version
				"1.0",
				//terms of service
				"Terms of Service",
				//contact details ("name", "website url", "email")
				new Contact("Aditya","","adiihcr0227@gmail.com"),
				//License
				"License of Apis",
				//license url
				"Api license url",
				// Collection on Vendor Extension
				Collections.emptyList()
				);
	}

}
