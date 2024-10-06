package com.example.demo.sweeping.repository;

import com.example.demo.sweeping.model.Activity;
import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT a FROM Activity a WHERE a.task.id=:task_id AND a.task.section=:section AND a.task.frequency=:frequency AND "+
            "CASE "+
            " WHEN :frequency = 'DAILY' THEN day(a.date) = day(now())"+
            " WHEN :frequency = 'WEEKLY' THEN week(a.date) = week(now())"+
            " WHEN :frequency = 'MONTHLY' THEN month(a.date) = month(now())"+
            " END")
    List<Activity> getActivitiesByTaskId(@Param("task_id")Long task_id, @Param("section") Section section, @Param("frequency") Frequency frequency);
    @Query(value = "SELECT a from Activity a WHERE day(a.date)=day(now()) AND a.task.frequency=:frequency AND a.task.section=:section")
    List<Activity> getDailyActivites(@Param("frequency") Frequency frequency, @Param("section") Section section);

    @Query(value = "SELECT a from Activity a WHERE month(a.date)=month(now()) AND a.task.frequency=:frequency AND a.task.section=:section")
    List<Activity> getMonthlyActivites(@Param("frequency") Frequency frequency, @Param("section") Section section);

    @Query(value = "SELECT a from Activity a WHERE week(a.date)=week(now()) AND a.task.frequency=:frequency AND a.task.section=:section")
    List<Activity> getWeeklyActivites(@Param("frequency") Frequency frequency, @Param("section") Section section);

    @Modifying
    @Query("delete from Activity a where a.id= :id")
    void deleteById(Long id);
}
