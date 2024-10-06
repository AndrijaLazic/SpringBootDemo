package com.example.demo.sweeping.mappers;

import com.example.demo.sweeping.dtos.TaskResponseDto;
import com.example.demo.sweeping.model.Task;
import com.example.demo.sweeping.model.TaskReportInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskReportInfoMapper {
    Task reportInfoToTask(TaskReportInfo reportInfo);
    TaskReportInfo taskToTaskReportInfo(Task task);
    List<TaskResponseDto> taskReportInfoToTaskResponseDtoList(List<TaskReportInfo> taskReportInfoList);
}
