package com.example.demo.sweeping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskReportInfo {
    Long id;
    String name;
    Frequency frequency;
    Section section;
    Long activityId;
    Date date;
    Date time;

    public TaskReportInfo(Long id, String name, Frequency frequency, Section section) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
        this.section = section;
    }
}
