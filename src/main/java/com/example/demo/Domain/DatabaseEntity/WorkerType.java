package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
//@Entity(name = "WorkerType")
//@Table(name = "WorkerTypes")
public class WorkerType {
    @Id
    @Column(name = "Id", columnDefinition = "tinyint not null")
    private Short id;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "TypeName", nullable = false, length = 20)
    private String typeName;

}