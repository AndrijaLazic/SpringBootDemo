package com.example.demo.sweeping.service.impl;

import com.example.demo.sweeping.dtos.ActivityResponseDto;
import com.example.demo.sweeping.dtos.UpdateActivityRequestDto;
import com.example.demo.sweeping.model.Section;
import com.example.demo.sweeping.repository.TaskRepository;
import com.example.demo.sweeping.mappers.ActivityMapper;
import com.example.demo.sweeping.model.Activity;
import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Task;
import com.example.demo.sweeping.repository.ActivityRepository;
import com.example.demo.sweeping.validators.exceptions.NoAvailableActivityException;
import com.example.demo.sweeping.validators.exceptions.NoAvailableTaskException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ActivityServiceImpl{

    private final ActivityRepository activityRepository;
    private final TaskRepository taskRepository;
    private final ActivityMapper activityMapper;

    @Transactional
    public Activity addActivity(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoAvailableTaskException("No available task."));
        Activity result = null;
        if(task.getFrequency().equals(Frequency.DAILY)){
            result= activityRepository.save(new Activity(task));
        }else{
            List<Activity> activities = activityRepository.getActivitiesByTaskId(task.getId(), task.getSection(), task.getFrequency());
            if (activities.isEmpty()) {
                result = activityRepository.save(new Activity(task));
            }
        }
        return result;
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public ActivityResponseDto updateActivity(Long id, UpdateActivityRequestDto updateActivityRequestDto) throws NoAvailableActivityException{
        Activity activity = activityRepository.findById(id).orElseThrow(() -> new NoAvailableActivityException("Activity not found."));
        try {
            Date f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateActivityRequestDto.getDateTime());
            activity.setDate(f);
            activity.setTime(f);
            activityRepository.save(activity);
            return activityMapper.toDto(activity);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Activity> getActivityByFrequencyAndSection(String frequency, String section) {
        List<Activity> allActivites=null;
        switch (Frequency.fromString(frequency)){
            case  DAILY-> allActivites=activityRepository.getDailyActivites(Frequency.fromString(frequency), Section.fromString(section));
            case MONTHLY -> allActivites=activityRepository.getMonthlyActivites(Frequency.fromString(frequency),Section.fromString(section));
            case WEEKLY -> allActivites=activityRepository.getWeeklyActivites(Frequency.fromString(frequency),Section.fromString(section));

        }
        return allActivites;
    }
}
