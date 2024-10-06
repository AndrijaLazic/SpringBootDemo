package com.example.demo.sweeping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Activity")
@Table(name = "activity", schema = "everyday_service_database", indexes = {
        @Index(name = "task_id", columnList = "task_id")
})
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @NotNull
    @Temporal(TemporalType.TIME)
    private Date time=new Date();

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Activity(Task task) {
        this.task = task;
    }
}
