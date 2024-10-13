package com.example.demo.ordering.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderNumberGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String HTEC = "HTEC";

    private OrderNumberGenerator(){

    }

    public static String generateOrderFilename(){
        return HTEC.concat("_").concat(getDateFormatted()).concat("_").concat("order");

    }

    public static String generate() {
        String localDateTime = getDateFormatted();

        long randomNumber  = Math.round(Math.random()*(99999 - 10000) + 10000);

        StringBuilder result = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            char index = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            result.append(index);
        }

        result.append(randomNumber);
        result.append("-");
        result.append(localDateTime);
        result.append("-");
        result.append(HTEC);
        return result.toString();
    }

    private static String getDateFormatted() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyy");
        return localDate.format(dateTimeFormatter);
    }
}
