package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "WorkerTypes")
public class WorkerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "TypeName", nullable = false, length = 20)
    private String typeName;

}