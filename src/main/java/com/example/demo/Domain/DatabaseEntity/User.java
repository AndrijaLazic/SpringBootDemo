package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Email", columnNames = {"Email"}),
        @UniqueConstraint(name = "UQ_PhoneNumber", columnNames = {"PhoneNumber"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 30)
    private String name;

    @Nationalized
    @Column(name = "Lastname", nullable = false, length = 30)
    private String lastname;

    @Nationalized
    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Column(name = "PasswordHash", nullable = false)
    private byte[] passwordHash;

    @Column(name = "PasswordSalt", nullable = false)
    private byte[] passwordSalt;

    @Nationalized
    @Column(name = "PhoneNumber", nullable = false, length = 30)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WorkerType", nullable = false)
    private com.example.demo.Domain.DatabaseEntity.WorkerType workerType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public com.example.demo.Domain.DatabaseEntity.WorkerType getWorkerType() {
        return workerType;
    }

    public void setWorkerType(com.example.demo.Domain.DatabaseEntity.WorkerType workerType) {
        this.workerType = workerType;
    }

}