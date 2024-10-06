package com.example.demo.sweeping.mappers;

import com.example.demo.sweeping.dtos.TaskActivityResponseDto;
import com.example.demo.sweeping.dtos.TaskResponseDto;
import com.example.demo.sweeping.model.Activity;
import com.example.demo.sweeping.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskResponseDto> toDto(List<Task> task);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "date", source = "date"),
            @Mapping(target = "taskName", source = "task.name"),
    })
    TaskActivityResponseDto toTaskActivityResponseDto(Activity activity);
}
