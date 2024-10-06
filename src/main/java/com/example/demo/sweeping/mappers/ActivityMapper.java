package com.example.demo.sweeping.mappers;

import java.text.SimpleDateFormat;

import com.example.demo.sweeping.dtos.ActivityResponseDto;
import com.example.demo.sweeping.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper
public interface ActivityMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "taskName", source = "task.name")
    })
    ActivityResponseDto toDto(Activity activity);

    @Named("dateTimeFormat")
    default String dateTimeFormat(Activity activity){
        if(activity.getDate() != null && activity.getTime() != null){
            String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(activity.getDate());
            String timeFormat = new SimpleDateFormat("hh:mm:ss").format(activity.getTime());
            return String.join(" ",dateFormat,timeFormat);
        }
        return null;
    }
}
