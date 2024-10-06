package com.example.demo.sweeping.util;

import com.example.demo.sweeping.model.Section;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SweepingUtils {

    private static final String MAINTANCE_REPORT = "maintance_report";
    private static final String DD_M_MYY = "ddMMyy";
    private static final String UNDERSCORE = "_";
    private static final String PDF = ".pdf";

    private SweepingUtils(){}

    public static String generateReportFilename(Section section) {
        StringBuilder result = new StringBuilder();
        result.append(MAINTANCE_REPORT);
        result.append(UNDERSCORE);
        result.append(section.name().toLowerCase());
        result.append(UNDERSCORE);
        result.append(LocalDate.now().format(DateTimeFormatter.ofPattern(DD_M_MYY)));
        result.append(PDF);
        return result.toString();
    }
}
