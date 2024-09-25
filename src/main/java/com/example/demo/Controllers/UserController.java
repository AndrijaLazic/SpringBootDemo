package com.example.demo.Controllers;

import com.example.demo.BLL.Services.AuthService;
import com.example.demo.Domain.DTO.UserRegistrationDTO;
import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.Shared.Exceptions.RequestResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    private AuthService authService;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private Environment env;

    @Autowired
    public UserController(AuthService authService, Environment env) {
        this.authService = authService;
        this.env = env;
        System.out.println(env.getProperty("TipProfila"));
    }

    @PostMapping("/Register")
    public RequestResponse<UserRegistrationDTO> sayHello(@Valid @RequestBody UserRegistrationDTO dto){
        RequestResponse<UserRegistrationDTO> requestResponse= new RequestResponse<UserRegistrationDTO>();

        try {
            authService.RegisterUser(dto);
        } catch (SQLDataException e) {
            logger.error(e.getMessage());
            requestResponse.success = false;
            requestResponse.message = e.getMessage();
            return requestResponse;
        }
        requestResponse.data = dto;
        requestResponse.success = true;
        return  requestResponse;
    }

    @GetMapping("/Users")
    public List<User> selectUsers() {
        try {
            return authService.getAllUsers();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/User")
    public User selectUser() {
        try {
            return authService.getUserByEmail("email");
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/Enviroment")
    public String enviroment() {
        return env.getProperty("TipProfila");
    }
}
