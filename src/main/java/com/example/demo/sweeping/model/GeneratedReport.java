package com.example.demo.sweeping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "generated_reports", schema = "everydaydb", uniqueConstraints = {
        @UniqueConstraint(name = "generated_reports_pk_2", columnNames = {"file_path"})
})
public class GeneratedReport {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 500)
    @NotNull
    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Size(max = 255)
    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

}