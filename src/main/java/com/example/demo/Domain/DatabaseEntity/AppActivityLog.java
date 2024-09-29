package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "AppActivityLog")
@Table(name = "app_activity_log")
public class AppActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private ParkingUser user;

    @Size(max = 20)
    @NotNull
    @Column(name = "action", nullable = false, length = 20)
    private String action;

    @Size(max = 30)
    @NotNull
    @Column(name = "target_table", nullable = false, length = 30)
    private String targetTable;

    @NotNull
    @Column(name = "time", nullable = false)
    private Instant time;

}