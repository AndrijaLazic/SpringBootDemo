package com.example.demo.sweeping.model;

import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class TaskReportTemplate {
    String mainTitle;
    String startDate;
    String endDate;

    Map<String,List<TaskReportInfo>> tasks;

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
        context.setVariable("mapOfTasks", tasks);

        return context;
    }
}
