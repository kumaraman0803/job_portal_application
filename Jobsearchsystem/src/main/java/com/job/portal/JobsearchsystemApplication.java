package com.job.portal;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
//@EnableWebSecurity
@Configuration
@SpringBootApplication
public class JobsearchsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsearchsystemApplication.class, args);
	}
	public Docket swaggerConfiguration() {
		 
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
	                .apis(RequestHandlerSelectors.basePackage("com.job.portal.jobseeker.controller")).build().apiInfo(apiInfo());
 
	   }
 
	   @SuppressWarnings("deprecation")
	    private ApiInfo apiInfo() {
	        
	        return new ApiInfoBuilder().title("Job Search Portal")
	                .description(" Job Search Portal")
	                .termsOfServiceUrl("http://world.com")
	                //.contact("world@gmail.com").license("My License")
	                .licenseUrl("world@gmail.com").version("1.0").build();
	    }
 
	
	
 
	}

	

