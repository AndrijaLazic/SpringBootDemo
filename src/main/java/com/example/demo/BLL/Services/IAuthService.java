package com.example.demo.BLL.Services;

import com.example.demo.Domain.DTO.UserRegistrationDTO;

import java.sql.SQLDataException;

public interface IAuthService {
    public void RegisterUser(UserRegistrationDTO userRegistrationDTO) throws SQLDataException;
}
