package com.example.demo.sweeping.service.impl;

import com.example.demo.sweeping.model.*;
import com.example.demo.sweeping.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Get all tasks with given parameters
     * @param state Current state of the task
     * @param startDate
     * @param endDate
     * @param frequency List of different frequencies you want to include in a search.
     * @param section List of different sections you want to include in a search.
     */
    public List<TaskReportInfo> getTasks(LocalDate startDate, LocalDate endDate, TaskState state, List<Frequency> frequency, List<Section> section) {
        switch(state) {
            case DONE:
                return taskRepository.getDoneTasksBetweenDates(startDate, endDate, frequency, section);
            case TODO:
                return taskRepository.getTODOTasksBetweenDates(startDate, endDate, frequency, section);
            default:
                return taskRepository.getAllBetweenDates(startDate, endDate, frequency, section);
        }
    }

    public List<Task> getTasksByFrequencyAndSection(Frequency frequency, Section section) {
        return taskRepository.getAllByFrequencyAndSection(frequency,section);
    }
}
