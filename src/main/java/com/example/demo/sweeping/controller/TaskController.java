package com.example.demo.sweeping.controller;

import com.example.demo.sweeping.dtos.TaskReportRequestDTO;
import com.example.demo.sweeping.dtos.TaskResponseDto;
import com.example.demo.sweeping.mappers.TaskReportInfoMapper;
import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.model.Task;
import com.example.demo.sweeping.service.impl.PdfGeneratorServiceImpl;
import com.example.demo.sweeping.service.impl.TaskService;
import com.example.demo.sweeping.util.SweepingUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor()
@Tag(name = "Task Controller", description = "Task operations")
public class TaskController {

    private final TaskService taskService;
    private final PdfGeneratorServiceImpl pdfGeneratorService;
    private final TaskReportInfoMapper taskReportInfoMapper;

    @PostMapping
    public List<TaskResponseDto> getTaskReport(@RequestBody TaskReportRequestDTO taskReportRequestDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startLocalDate = LocalDate.parse(taskReportRequestDTO.getStartDate(), formatter);
        LocalDate endLocalDate = LocalDate.parse(taskReportRequestDTO.getEndDate(), formatter);
        return taskReportInfoMapper.taskReportInfoToTaskResponseDtoList(
                taskService.getTasks(startLocalDate, endLocalDate, taskReportRequestDTO.getState(), taskReportRequestDTO.getFrequencies(), taskReportRequestDTO.getSections())
        );
    }


    @PostMapping("/report")
    public ResponseEntity<byte[]> createTaskReport(@RequestBody TaskReportRequestDTO taskReportRequestDTO) {
        String finalHtml = pdfGeneratorService.parseThymeleafTemplateTaskReport(taskReportRequestDTO);
        byte[] pdfFile = pdfGeneratorService.convertToPdf(finalHtml);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.setContentDispositionFormData("filename", SweepingUtils.generateReportFilename(Section.KITCHEN));
        header.setContentLength(pdfFile.length);

        return new ResponseEntity<>(pdfFile,header, HttpStatus.OK);
    }
}
