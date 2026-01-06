package com.tushar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AirbnbCloneProjectApplication {
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addViewControllers(ViewControllerRegistry registry) {
	            registry.addViewController("/").setViewName("forward:/index.html");
	        }
	    };
	}

	public static void main(String[] args) {
		SpringApplication.run(AirbnbCloneProjectApplication.class, args);
	}

}
