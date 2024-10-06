package com.example.demo.sweeping.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Frequency {
    DAILY,
    WEEKLY,
    MONTHLY;

    public static List<Frequency> getAllValues() {
        return Arrays.asList(Frequency.values());
    }

    public static Frequency fromString(String name) throws IllegalArgumentException{
        for(Frequency frequency : Frequency.values()){
            if(frequency.name().equalsIgnoreCase(name)){
                return frequency;
            }
        }
        throw new IllegalArgumentException();
    }

}
