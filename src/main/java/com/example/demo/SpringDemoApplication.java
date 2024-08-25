package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(IUserDAO userDAO) {
//        return (args) -> {
//			User user = new User();
//			user.setName("brzi");
//			user.setEmail("brzi@gmail.com");
//			user.setPasswordHash("SESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSES".getBytes(StandardCharsets.UTF_8));
//			user.setPasswordSalt("SESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSESSES".getBytes(StandardCharsets.UTF_8));
//			user.setPhoneNumber("12312");
//			user.setLastname("SEX");
//			userDAO.addUser(user);
//		};
//	}
}
