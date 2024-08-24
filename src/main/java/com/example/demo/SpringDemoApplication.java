package com.example.demo;

import com.example.demo.Domain.DAO.IUserDAO;
import com.example.demo.Domain.DatabaseEntity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUserDAO userDAO) {
        return (args) -> {
			User user = new User();
			user.setName("brzi");
			user.setEmail("brzi@gmail.com");
			user.setPasswordHash("SESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSES".getBytes(StandardCharsets.UTF_8));
			user.setPasswordSalt("SESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSES".getBytes(StandardCharsets.UTF_8));
			user.setPhoneNumber("12312");
			user.setLastname("SEX");
			userDAO.addUser(user);
		};
	}
}
