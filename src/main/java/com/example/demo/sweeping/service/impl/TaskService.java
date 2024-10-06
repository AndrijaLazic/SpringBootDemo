package com.example.demo.sweeping.service.impl;

import com.example.demo.sweeping.model.*;
import com.example.demo.sweeping.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

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
     * @return returns a Map that contains date as key and List of tasks as value
     */
    public Map<String,List<TaskReportInfo>> getTasksForDateRange(LocalDate startDate, LocalDate endDate, TaskState state, List<Frequency> frequency, List<Section> section) {
        SortedMap<String,List<TaskReportInfo>> tasks = new TreeMap<>();

        if(state == TaskState.DONE){
            List<TaskReportInfo> taskReportInfos = taskRepository.getDONETasksBetweenDates(startDate, endDate, frequency, section);

            for (int i = 0; i < taskReportInfos.size(); i++) {
                TaskReportInfo taskReportInfo = taskReportInfos.get(i);
                List<TaskReportInfo> listOfTasks = tasks.get(taskReportInfo.getDate().toString());
                if(listOfTasks==null){
                    listOfTasks = new LinkedList<>();
                    tasks.put(taskReportInfo.getDate().toString(),listOfTasks);
                }
                listOfTasks.add(taskReportInfo);
                tasks.put(startDate.toString(), listOfTasks);
            }
            return tasks;
        }

        while (!startDate.isAfter(endDate)) {
            if(state == TaskState.TODO){
                tasks.put(startDate.toString(), taskRepository.getTODOTasksForDate(startDate, frequency, section));
                startDate = startDate.plusDays(1);
                continue;
            }
            tasks.put(startDate.toString(), taskRepository.getTasksForDate(startDate, frequency, section));
            startDate = startDate.plusDays(1);
        }
        return tasks;
    }

    /**
     * Get all tasks with given parameters
     * @param state Current state of the task
     * @param date
     * @param frequency List of different frequencies you want to include in a search.
     * @param section List of different sections you want to include in a search.
     */
    public List<TaskReportInfo> getTasksForDate(LocalDate date,TaskState state, List<Frequency> frequency, List<Section> section) {
        switch (state) {
            case DONE:
                return taskRepository.getDONETasksBetweenDates(date,date,frequency,section);
            case TODO:
                return taskRepository.getTODOTasksForDate(date,frequency,section);
            default:
                return taskRepository.getTasksForDate(date,frequency,section);
        }
    }

    public List<Task> getTasksByFrequencyAndSection(Frequency frequency, Section section) {
        return taskRepository.getAllByFrequencyAndSection(frequency,section);
    }
}
