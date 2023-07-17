package com.gdsc.CGLH;

import com.gdsc.CGLH.controller.UserController;
import com.gdsc.CGLH.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class CglhApplication {

	public static void main(String[] args) {
		SpringApplication.run(CglhApplication.class, args);
	}

}
