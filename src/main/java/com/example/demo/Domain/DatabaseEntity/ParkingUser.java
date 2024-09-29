package com.example.demo.Domain.DatabaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "ParkingUser")
@Table(name = "parking_user")
public class ParkingUser {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 255)
    @NotNull
    @Column(name = "seniority_type", nullable = false)
    private String seniorityType;

    @Column(name = "position")
    private Short position;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    @Column(name = "disability")
    private Boolean disability;

}