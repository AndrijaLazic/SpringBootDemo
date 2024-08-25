package com.example.demo.DAL.DAO;


import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;

public interface IUserDAO {
    void addUser(User user);
    WorkerType getWorkerType(int id);
}
