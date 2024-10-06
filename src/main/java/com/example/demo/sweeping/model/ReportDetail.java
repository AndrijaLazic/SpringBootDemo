package com.example.demo.sweeping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetail {
    private String name;
    private Date date;
    private List<String> time;

    @Override
    public String toString() {
        return "ReportDetail{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
