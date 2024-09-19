package com.example.demo.DAL.DAO;


import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;

import java.util.List;

public interface IUserDAO {
    void addUser(User user);
    WorkerType getWorkerType(int id);
    List<User> getAllUsers();

    User getUser(int id);
    User getUser(String email);
}
