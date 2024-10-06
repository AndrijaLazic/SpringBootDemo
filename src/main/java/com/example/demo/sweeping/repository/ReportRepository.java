package com.example.demo.sweeping.repository;
import com.example.demo.sweeping.model.Activity;
import com.example.demo.sweeping.model.Frequency;
import com.example.demo.sweeping.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Activity, Long> {

      @Query(value = "SELECT t.name, a.date, GROUP_CONCAT(a.time) AS time FROM Activity a JOIN Task t ON a.task.id=t.id WHERE " +
            "t.frequency = :frequency AND t.section = :section AND (a.date>=:firstDay AND a.date<=:lastDay) GROUP BY t.name,a.date")
    List<Object[]> findAllFinishedActivities(@Param("frequency") Frequency frequency, @Param("section") Section section, @Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate lastDay);

      @Query("SELECT a.date, GROUP_CONCAT(a.time) FROM Activity a WHERE a.task.section=:section AND a.task.frequency=:frequency AND MONTH(NOW())=MONTH(a.date) "+
      "GROUP BY a.date")
    List<Object[]> findAllActivitiesForCurrentMonth(@Param("frequency")Frequency frequency, @Param("section")Section section);


}
