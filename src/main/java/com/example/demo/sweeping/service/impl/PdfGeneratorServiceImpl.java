package com.example.demo.sweeping.service.impl;


import com.example.demo.sweeping.dtos.TaskReportRequestDTO;
import com.example.demo.sweeping.model.*;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.AllArgsConstructor;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PdfGeneratorServiceImpl {
    private final ResourceLoader resourceLoader;
    private final ReportServiceImpl reportService;
    private final TaskService taskService;
    private final SpringTemplateEngine templateEngine;

    public byte[] convertToPdf(String html) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(defaultFontProvider);

            HtmlConverter.convertToPdf(html, pdfDocument, converterProperties);
            pdfDocument.close();

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String parseThymeleafTemplate(){
        Context context = new Context();

        Map<LocalDate, List<String>> activities = reportService.getDailyBathroomActivities();
        List<Task> monthlyTasks = taskService.getTasksByFrequencyAndSection(Frequency.MONTHLY, Section.BATHROOM);

        context.setVariable("currentDate", reportService.getCurrentDate());
        context.setVariable("activities", activities);
        context.setVariable("monthlyTasks", monthlyTasks);

        return templateEngine.process("reportBathroom", context);
    }

    public String parseThymeleafTemplateKitchen(){
        Context context = new Context();

        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        Map<String, List<ReportDetail>> activityListDaily = reportService.getDoneActivities(Frequency.DAILY, Section.KITCHEN, firstDayOfMonth, lastDayOfMonth);

        Map<String, List<ReportDetail>>[] dataPerWeeks=reportService.tasksPerWeeks(activityListDaily);
        Map<String, List<ReportDetail>> week1 = dataPerWeeks[0];
        Map<String, List<ReportDetail>> week2 = dataPerWeeks[1];
        Map<String, List<ReportDetail>> week3 = dataPerWeeks[2];

        List<Date> distinctDates=reportService.datesOfWeek(activityListDaily);
        List<Date> week1Dates = new ArrayList<>(distinctDates.subList(0, 1));
        List<Date> week2Dates = new ArrayList<>(distinctDates.subList(1, 6));

        List<Date> week3Dates=null;
        if(distinctDates.size()>=11) {
            week3Dates = new ArrayList<>(distinctDates.subList(6, 11));
        }
        else {
            week3Dates = new ArrayList<>(distinctDates.subList(6, 10));
        }

        Map<String, List<ReportDetail>> activityListWeekly = reportService.getDoneActivities(Frequency.WEEKLY, Section.KITCHEN, firstDayOfMonth, lastDayOfMonth);
        Map<String, List<ReportDetail>> activityListMonthly = reportService.getDoneActivities(Frequency.MONTHLY, Section.KITCHEN, firstDayOfMonth, lastDayOfMonth);

        context.setVariable("currentMonth", firstDayOfMonth.toString().substring(0,7));
        context.setVariable("activityListDailyWeek1", week1);context.setVariable("datesForWeek1", week1Dates);
        context.setVariable("activityListDailyWeek2", week2);context.setVariable("datesForWeek2", week2Dates);
        context.setVariable("activityListDailyWeek3", week2);context.setVariable("datesForWeek3", week3Dates);

        context.setVariable("activityListWeekly", activityListWeekly);
        context.setVariable("activityListMonthly", activityListMonthly);

        return templateEngine.process("reportActivityKichen", context);
    }

    public String parseThymeleafTemplateTaskReport(TaskReportRequestDTO request){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startLocalDate = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate endLocalDate = LocalDate.parse(request.getEndDate(), formatter);
        Map<String, List<TaskReportInfo>> tasks = taskService.getTasksForDateRange(startLocalDate, endLocalDate, request.getState(), request.getFrequencies(), request.getSections());

        TaskReportTemplate reportTemplate = new TaskReportTemplate("Izvestaj", request.getStartDate(), request.getEndDate());
        reportTemplate.setTasks(tasks);

        Context context = reportTemplate.createContext();

        return templateEngine.process("TaskReportTemplate", context);
    }


}
