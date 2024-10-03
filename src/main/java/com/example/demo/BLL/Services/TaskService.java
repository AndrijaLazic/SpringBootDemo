package com.example.demo.BLL.Services;

import com.example.demo.Domain.DTO.TaskRepository;
import com.example.demo.Domain.DatabaseEntity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public List<Task> getTasks(LocalDate startDate, LocalDate endDate, String frequency, String section) {
        return taskRepository.getDoneTasksBetweenDates(startDate, endDate, frequency, section);
    }

    @Transactional
    public List<Task> getTODOTasks(LocalDate startDate, LocalDate endDate, String frequency, String section) {
        return taskRepository.getTODOTasksBetweenDates(startDate, endDate, frequency, section);
    }
}
