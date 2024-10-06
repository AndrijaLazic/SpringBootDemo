package com.example.demo.sweeping.dtos;

import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private Frequency frequency;
    private String name;
    private Section section;
}
