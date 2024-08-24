package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "WorkerTypes")
public class WorkerType {
    @Id
    @Column(name = "Id", columnDefinition = "tinyint not null")
    private Short id;

    @Nationalized
    @Column(name = "TypeName", nullable = false, length = 20)
    private String typeName;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}