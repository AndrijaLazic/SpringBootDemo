package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
//@Entity(name = "User")
//@NoArgsConstructor
//@Table(name = "Users", uniqueConstraints = {
//        @UniqueConstraint(name = "UQ_Email", columnNames = {"Email"}),
//        @UniqueConstraint(name = "UQ_PhoneNumber", columnNames = {"PhoneNumber"})
//})
//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(
//                name = "sp_insert_user",
//                procedureName = "sp_insert_user",
//                parameters={
//                        @StoredProcedureParameter(name="Name", type=String.class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="Lastname", type=String.class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="Email", type=String.class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="PasswordHash", type=byte[].class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="PasswordSalt", type=byte[].class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="PhoneNumber", type=String.class, mode=ParameterMode.IN),
//                        @StoredProcedureParameter(name="WorkerType", type=int.class, mode=ParameterMode.IN),
//                }
//        )
//})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Nationalized
    @Column(name = "Name", nullable = false, length = 30)
    private String name;

    @Size(max = 30)
    @NotNull
    @Nationalized
    @Column(name = "Lastname", nullable = false, length = 30)
    private String lastname;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "PasswordHash", nullable = false)
    private byte[] passwordHash;

    @NotNull
    @Column(name = "PasswordSalt", nullable = false)
    private byte[] passwordSalt;

    @Size(max = 30)
    @NotNull
    @Nationalized
    @Column(name = "PhoneNumber", nullable = false, length = 30)
    private String phoneNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "WorkerType", nullable = false)
    private WorkerType workerType;

    @Column(name = "IsValid", nullable = false)
    private Boolean isValid = false;

    public User(String name, String lastname, String email, @NotNull byte[] passwordHash, @NotNull byte[] passwordSalt, String phoneNumber, WorkerType workerType) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.phoneNumber = phoneNumber;
        this.workerType = workerType;
    }
}