package com.spring.app;

import com.spring.app.security.config.ApplicationSecurityConfig;
import com.spring.app.security.config.PasswordConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@Import({ApplicationSecurityConfig.class,
		 PasswordConfig.class})
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}
