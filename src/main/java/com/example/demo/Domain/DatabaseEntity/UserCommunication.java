package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "UserCommunication", indexes = {
        @Index(name = "IX_UniquePair", columnList = "User1, User2", unique = true)
})
public class UserCommunication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User1", nullable = false)
    private com.example.demo.Domain.DatabaseEntity.User user1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "User2", nullable = false)
    private com.example.demo.Domain.DatabaseEntity.User user2;

    @ColumnDefault("0")
    @Column(name = "User1Unread", nullable = false)
    private Integer user1Unread;

    @ColumnDefault("0")
    @Column(name = "User2Unread", nullable = false)
    private Integer user2Unread;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.example.demo.Domain.DatabaseEntity.User getUser1() {
        return user1;
    }

    public void setUser1(com.example.demo.Domain.DatabaseEntity.User user1) {
        this.user1 = user1;
    }

    public com.example.demo.Domain.DatabaseEntity.User getUser2() {
        return user2;
    }

    public void setUser2(com.example.demo.Domain.DatabaseEntity.User user2) {
        this.user2 = user2;
    }

    public Integer getUser1Unread() {
        return user1Unread;
    }

    public void setUser1Unread(Integer user1Unread) {
        this.user1Unread = user1Unread;
    }

    public Integer getUser2Unread() {
        return user2Unread;
    }

    public void setUser2Unread(Integer user2Unread) {
        this.user2Unread = user2Unread;
    }

}