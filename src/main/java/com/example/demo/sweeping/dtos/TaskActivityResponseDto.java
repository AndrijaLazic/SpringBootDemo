package com.example.demo.sweeping.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskActivityResponseDto {
    private Long id;
    private String taskName;
    private Date date;
}
