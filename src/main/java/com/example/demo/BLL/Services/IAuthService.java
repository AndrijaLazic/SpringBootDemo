package com.example.demo.BLL.Services;

import com.example.demo.Domain.DTO.UserRegistrationDTO;
import com.example.demo.Domain.DatabaseEntity.User;

import java.sql.SQLDataException;
import java.util.List;

public interface IAuthService {
    public void RegisterUser(UserRegistrationDTO userRegistrationDTO) throws SQLDataException;
    public List<User> getAllUsers() throws SQLDataException;
    public User getUserByEmail(String email) throws SQLDataException;
}
