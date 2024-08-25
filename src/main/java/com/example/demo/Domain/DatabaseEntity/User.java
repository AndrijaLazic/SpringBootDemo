package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Email", columnNames = {"Email"}),
        @UniqueConstraint(name = "UQ_PhoneNumber", columnNames = {"PhoneNumber"})
}
)
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

    public User(String name, String lastname, String email, byte[] passwordHash, byte[] passwordSalt, String phoneNumber,WorkerType workerType) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.phoneNumber = phoneNumber;
        this.workerType = workerType;
    }
}