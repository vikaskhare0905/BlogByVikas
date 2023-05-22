package com.blogAppApi.config;


import java.util.Arrays;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.yaml.snakeyaml.tokens.DocumentEndToken;

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

		public static final String AUTHORIZATION_HEADER="Authorization";
		
		//Spring Security JWT token
		private ApiKey apiKeys()
		{
			return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
		}
		
		//Spring Security JWT token
		private  List<SecurityContext> securityContexts()
		{
			return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
			
		}
		
		//Spring Security JWT token
		private List<SecurityReference> sf()
		{
			AuthorizationScope scope= new AuthorizationScope("global", "accessEverything");
			return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[] {scope}));
		}
		
		//For Swagger documentation
		@Bean
		public Docket api() {
				
			return new Docket(DocumentationType.SWAGGER_2).
					apiInfo(getInfo()).securityContexts(securityContexts()).
					securitySchemes(Arrays.asList(apiKeys())).
					select().
					apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any()).build();

		}

		private ApiInfo getInfo() {

			return new ApiInfo("Blog Application : Backend Development", "This project is developed by VIKAS", "1.0",
					"Terms of service", new Contact("VIKAS", "https://vikaskhare09@gmail.com", "vksolution.com"),
					"Licence of APIs", "API licence URL", Collections.emptyList());
		}
	}

