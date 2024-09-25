package com.example.demo.BLL.Services;

import com.example.demo.Domain.DTO.UserRegistrationDTO;
import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;
import jakarta.servlet.Registration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/test.properties")
class AuthServiceTest {

    @Autowired
    AuthService authService;

    UserRegistrationDTO registrationDTO = new UserRegistrationDTO(
            "KorisnikIme",
            "KorisnikPrezime",
            "Email",
            "061556",
            "string",
            1
    );


//    @Test
//    void registerUserSuccess() {
//        try{
//            authService.RegisterUser(registrationDTO);
//        }
//        catch(Exception e){
//            fail("Failed to register user");
//        }
//    }
//
//    @Test
//    void registerUserFailure_Duplicate() {
//        assertThrows(Exception.class, ()->authService.RegisterUser(registrationDTO));
//    }
//
//    @Test
//    void getAllUsers() {
//        try {
//            List<User> users = authService.getAllUsers();
//            assertEquals(new ArrayList<>(), users);
//
//        } catch (SQLDataException e) {
//            fail("Failed to get users");
//        }
//    }
//
//    @Test
//    void getUserByEmail() {
//    }
}