package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "UserCommunication", schema = "dbo", indexes = {
        @Index(name = "IX_UniquePair", columnList = "User1, User2", unique = true)
})
public class UserCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User1", nullable = false)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User2", nullable = false)
    private User user2;

    @ColumnDefault("0")
    @Column(name = "User1Unread", nullable = false)
    private Integer user1Unread;

    @ColumnDefault("0")
    @Column(name = "User2Unread", nullable = false)
    private Integer user2Unread;

}