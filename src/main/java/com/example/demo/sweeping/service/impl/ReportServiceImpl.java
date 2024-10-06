package com.example.demo.sweeping.service.impl;

import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.ReportDetail;
import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReportServiceImpl{

    private ReportRepository reportRepository;
    public  Map<String, List<ReportDetail>> getDoneActivities(Frequency frequency, Section section , LocalDate firstDay, LocalDate lastDay) {

        List<Object[]> reportDetails = reportRepository.findAllFinishedActivities(frequency, section, firstDay, lastDay);

        Map<String, List<ReportDetail>> tasks=new HashMap<>();

        for (Object[] result : reportDetails) {
            String name = (String) result[0];
            Date date = (Date) result[1];
            String times = (String) result[2];

            if (tasks.containsKey(name)) {
                List<ReportDetail> list = tasks.get(name);
                ReportDetail taskGroup = createReportDetail(name, date, times);

                list.add(taskGroup);
            }
            else{
                ReportDetail reportDetail = createReportDetail(name, date, times);
                List<ReportDetail> reportValues = new ArrayList<>();
                reportValues.add(reportDetail);
                tasks.put(name,reportValues);
            }
        }

        return tasks;
    }

    private static ReportDetail createReportDetail(String name, Date date, String times) {
        ReportDetail taskGroup = new ReportDetail();
        taskGroup.setName(name);
        taskGroup.setDate(date);
        List<String> timesList = new ArrayList<>();
        if (times != null && !times.isEmpty()) {
            for (String time : times.split(",")) {
                timesList.add(time.trim());
            }
        }
        taskGroup.setTime(timesList);
        return taskGroup;
    }
    public List<Date> datesOfWeek( Map<String, List<ReportDetail>> activityListDaily){
        List<Date> allDates = new ArrayList<>();

        for (List<ReportDetail> reportDetails : activityListDaily.values()) {
            for (ReportDetail reportDetail : reportDetails) {
                Date date = reportDetail.getDate();
                allDates.add(date);
            }
        }

        List<Date> distinctDates = allDates.stream().distinct().collect(Collectors.toList());

        return distinctDates;
    }
    public Map<String, List<ReportDetail>>[] tasksPerWeeks(Map<String, List<ReportDetail>> activityListDaily){

        Map<String, List<ReportDetail>> week1 = new HashMap<>();
        Map<String, List<ReportDetail>> week2 = new HashMap<>();
        Map<String, List<ReportDetail>> week3 = new HashMap<>();

        for (Map.Entry<String, List<ReportDetail>> entry : activityListDaily.entrySet()) {
            String key = entry.getKey();
            List<ReportDetail> value = entry.getValue();

            value.sort(Comparator.comparing(ReportDetail::getDate));

            List<ReportDetail> week1Value = new ArrayList<>();
            List<ReportDetail> week2Value = new ArrayList<>();
            List<ReportDetail> week3Value = new ArrayList<>();

            for (ReportDetail reportDetail : value) {

                Date date = reportDetail.getDate();
                java.util.Date utilDate = new java.util.Date(date.getTime());
                LocalDate eventDateTime = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                WeekFields weekFields = WeekFields.of(Locale.getDefault());
                int weekNum = eventDateTime.get(weekFields.weekOfMonth());

                switch (weekNum) {
                    case 1:
                        week1Value.add(reportDetail);
                        break;
                    case 2:
                        week2Value.add(reportDetail);
                        break;
                    case 3:
                        week3Value.add(reportDetail);
                        break;
                }
            }

            week1.put(key, week1Value);
            week2.put(key, week2Value);
            week3.put(key, week3Value);

        }
            Map<String, List<ReportDetail>>[] weeks = new Map[3];
            weeks[0] = week1;
            weeks[1] = week2;
            weeks[2] = week3;

            return weeks;
    }
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return  new SimpleDateFormat("yyyy/MM").format(calendar.getTime());
    }

    public Map<LocalDate, List<String>> getDailyBathroomActivities() {
        List<Object[]> result = reportRepository.findAllActivitiesForCurrentMonth(Frequency.DAILY, Section.BATHROOM);
        Map<LocalDate, List<String>> activities = new LinkedHashMap<>();

        for (Object[] activity : result){
            List<String> tourTimes = Arrays.asList(activity[1].toString().split(","));
            activities.put(LocalDate.parse(activity[0].toString()), tourTimes);
        }
        return activities;
    }


}
