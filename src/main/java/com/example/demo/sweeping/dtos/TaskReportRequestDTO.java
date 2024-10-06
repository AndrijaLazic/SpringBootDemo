package com.example.demo.sweeping.dtos;

import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.model.TaskState;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class TaskReportRequestDTO {
    private List<Frequency> frequencies = Frequency.getAllValues();
    private List<Section> sections = Section.getAllValues();
    private TaskState state = TaskState.ALL;
    private String startDate;
    private String endDate;
}

