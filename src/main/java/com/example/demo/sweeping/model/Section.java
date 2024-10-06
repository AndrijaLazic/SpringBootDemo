package com.example.demo.sweeping.model;

import java.util.Arrays;
import java.util.List;

public enum Section {
    OFFICE,
    KITCHEN,
    BATHROOM;

    public static List<Section> getAllValues() {
        return Arrays.asList(Section.values());
    }

    public static Section fromString(String name) throws IllegalArgumentException{
        for(Section section : Section.values()){
            if(section.name().equalsIgnoreCase(name)){
                return section;
            }
        }
        throw new IllegalArgumentException();
    }
}
