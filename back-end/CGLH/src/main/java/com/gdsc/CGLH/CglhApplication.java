package com.gdsc.CGLH;

import com.gdsc.CGLH.controller.UserController;
import com.gdsc.CGLH.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = UserService.class)
public class CglhApplication {

	public static void main(String[] args) {
		SpringApplication.run(CglhApplication.class, args);
	}

}
