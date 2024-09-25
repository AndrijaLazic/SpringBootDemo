package com.example.demo;

import com.example.demo.Controllers.UserController;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {
	Logger logger = LoggerFactory.getLogger(SpringDemoApplication.class);
    public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);

	}
}
