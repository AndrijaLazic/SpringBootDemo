package com.example.demo.DAL.DAO;

import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAO implements  IUserDAO{

    private EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public WorkerType getWorkerType(int id) {
        return entityManager.find(WorkerType.class, id);
    }
}
