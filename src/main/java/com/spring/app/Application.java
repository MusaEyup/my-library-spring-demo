package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@SpringBootApplication
@ComponentScan(basePackages = {
							   "com.spring.app.dao.repository",
							   "com.spring.app.service",
							   "com.spring.app.rest.controller"})
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
