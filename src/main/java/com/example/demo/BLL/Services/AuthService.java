package com.example.demo.BLL.Services;

import com.example.demo.DAL.DAO.UserDAO;
import com.example.demo.Domain.DTO.UserRegistrationDTO;
import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;
import com.example.demo.Domain.Shared.PasswordClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLDataException;
import java.util.Arrays;

@Service
public class AuthService implements IAuthService {

    private UserDAO userDAO;
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void RegisterUser(UserRegistrationDTO userRegistrationDTO) throws SQLDataException {

        WorkerType workerType = userDAO.getWorkerType(userRegistrationDTO.getWorkerType());
        if(workerType == null) {
            throw  new RuntimeException("Worker type not found");
        }
        byte[] salt = PasswordClass.GetSalt();
        try {
            byte[] hash = PasswordClass.GetHast(salt,userRegistrationDTO.getPassword());
            User user =userRegistrationDTO.DtoToUser(hash,salt,workerType);
            userDAO.addUser(user);
        } catch (NoSuchAlgorithmException| InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (DataIntegrityViolationException e){
            throw new SQLDataException(e.getMessage());
        }

        logger.info(userRegistrationDTO.toString());
    }
}
