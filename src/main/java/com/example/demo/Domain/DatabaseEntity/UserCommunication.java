package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity(name = "UserCommunication")
@Table(name = "UserCommunication")
public class UserCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User1", nullable = false)
    private User user1;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User2", nullable = false)
    private User user2;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "User1Unread", nullable = false)
    private Integer user1Unread;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "User2Unread", nullable = false)
    private Integer user2Unread;

}