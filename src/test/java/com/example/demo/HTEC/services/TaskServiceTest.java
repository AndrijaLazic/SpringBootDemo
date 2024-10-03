package com.example.demo.HTEC.services;

import com.example.demo.BLL.Services.TaskService;
import com.example.demo.Domain.DatabaseEntity.Task;
import com.example.demo.Domain.Shared.Frequency;
import com.example.demo.Domain.Shared.Section;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@TestPropertySource("/application-local.properties")
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Test
    void getTasks() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date1 = LocalDate.parse("2024-03-01",dtf);
        LocalDate date2 = LocalDate.parse("2024-03-04",dtf);
        String freq = Frequency.DAILY.toString();
        String sect = Section.KITCHEN.toString();
        List<Task> tasks = taskService.getTasks(
                date1,
                date2,
                freq,
                sect
        );

        List<Task> tasks2 = taskService.getTODOTasks(
                date1,
                date2,
                freq,
                sect
        );
    }
}