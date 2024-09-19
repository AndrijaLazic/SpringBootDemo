package com.example.demo.DAL.DAO;

import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        //entityManager.persist(user);

        try{
            StoredProcedureQuery storedProcedure = entityManager
                    .createNamedStoredProcedureQuery("sp_insert_user");

            storedProcedure.setParameter("Name", user.getName())
                    .setParameter("Lastname", user.getLastname())
                    .setParameter("Email", user.getEmail())
                    .setParameter("PasswordHash", user.getPasswordHash())
                    .setParameter("PasswordSalt", user.getPasswordSalt())
                    .setParameter("PhoneNumber", user.getPhoneNumber())
                    .setParameter("WorkerType", user.getWorkerType().getId());

            storedProcedure.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WorkerType getWorkerType(int id) {
        return entityManager.find(WorkerType.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUser(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE email like :email");
        query.setParameter("email", email);
        var sex = query.getSingleResult();
        return (User) sex;
    }


}
