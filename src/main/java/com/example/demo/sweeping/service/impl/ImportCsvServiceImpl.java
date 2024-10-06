package com.example.demo.sweeping.service.impl;

import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.model.Task;
import com.example.demo.sweeping.repository.TaskRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Service
@AllArgsConstructor
public class ImportCsvServiceImpl{

    private final TaskRepository taskRepository;

    private final ResourceLoader resourceLoader;

    @Transactional
    public void importCsvFile() throws IOException {
        if(taskRepository.count() == 0) {
            try{
                InputStream inputStream = resourceLoader.getResource("classpath:tasks.csv").getInputStream();
                CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
                List<Task> tasks = new ArrayList<>();
                List<String[]> rows = csvReader.readAll();
                Section currentSection = null;
                for (String[] row : rows) {
                    String trimmedSection = row[0].trim().replaceAll("\\s+"," ");
                    if(trimmedSection.startsWith("#")){
                        currentSection = Section.fromString(trimmedSection.split(" ")[1]);
                        continue;
                    }
                    tasks.add(Task.builder()
                        .name(row[0])
                        .frequency(Frequency.fromString(row[1]))
                        .section(currentSection)
                        .build());
                }
                taskRepository.saveAll(tasks);
            }catch (FileNotFoundException e){
                throw new FileNotFoundException();
            }catch (CsvException e) {
                throw new RuntimeException();
            }catch (IOException e) {
                throw new IOException();
            }
        }
    }
}
