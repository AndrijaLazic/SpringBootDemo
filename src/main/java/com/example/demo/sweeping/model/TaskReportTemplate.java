package com.example.demo.sweeping.model;

import com.example.demo.sweeping.dtos.TaskReportRequestDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class TaskReportTemplate {
    String mainTitle;
    String startDate;
    String endDate;

    List<TaskReportInfo> tasks;

    public TaskReportTemplate(String mainTitle, String startDate, String endDate) {
        this.mainTitle = mainTitle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Context createContext() {
        Context context = new Context();
        context.setVariable("mainTitle", this.mainTitle);
        context.setVariable("startDate", this.startDate);
        context.setVariable("endDate", this.endDate);
        context.setVariable("tasks", tasks);

        return context;
    }
}
