package com.example.demo.sweeping.controller;

import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.service.impl.PdfGeneratorServiceImpl;
import com.example.demo.sweeping.util.SweepingUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/sweeping/report")
@AllArgsConstructor
public class PdfController {
    private final PdfGeneratorServiceImpl pdfGeneratorService;
    @GetMapping("/toilet")
    public ResponseEntity<byte[]> downloadReport(){

        String finalHtml = pdfGeneratorService.parseThymeleafTemplate();
        byte[] pdfFile = pdfGeneratorService.convertToPdf(finalHtml);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.setContentDispositionFormData("filename", SweepingUtils.generateReportFilename(Section.BATHROOM));
        header.setContentLength(pdfFile.length);

        return new ResponseEntity<>(pdfFile,header, HttpStatus.OK);

    }

    @GetMapping("/kitchen")
    public ResponseEntity<byte[]> downloadReportKitchen(){

        String finalHtml = pdfGeneratorService.parseThymeleafTemplateKitchen();
        byte[] pdfFile = pdfGeneratorService.convertToPdf(finalHtml);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.setContentDispositionFormData("filename", SweepingUtils.generateReportFilename(Section.KITCHEN));
        header.setContentLength(pdfFile.length);

        return new ResponseEntity<>(pdfFile,header, HttpStatus.OK);

    }
}
