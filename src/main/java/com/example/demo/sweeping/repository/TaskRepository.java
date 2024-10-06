package com.example.demo.sweeping.repository;


import com.example.demo.sweeping.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Get DONE tasks
     * @param dateStart
     * @param dateEnd
     * @param frequency List of different frequencies you want to include in a search.
     * @param section List of different sections you want to include in a search.
     */
    @Query("SELECT new com.example.demo.sweeping.model.TaskReportInfo(t.id,t.name,t.frequency,t.section,a.id,a.date,a.time) FROM Task t " +
           "JOIN Activity a ON t.id = a.task.id " +
           "WHERE t.frequency IN (:#{#frequency.![name()]}) AND t.section IN (:#{#section.![name()]}) AND (a.date BETWEEN :dateStart AND :dateEnd)")
    List<TaskReportInfo> getDONETasksBetweenDates(@Param("dateStart") LocalDate dateStart,
                                                  @Param("dateEnd") LocalDate dateEnd,
                                                  @Param("frequency") List<Frequency> frequency,
                                                  @Param("section") List<Section> section);

    /**
     * Get TODO tasks
     * @param date
     * @param frequency List of different frequencies you want to include in a search.
     * @param section List of different sections you want to include in a search.
     */
    @Query("SELECT new com.example.demo.sweeping.model.TaskReportInfo(t1.id,t1.name,t1.frequency,t1.section) FROM Task t1 " +
            "WHERE t1.id NOT IN (" +
                "SELECT t.id FROM Task t " +
                "JOIN Activity a ON t.id = a.task.id " +
                "WHERE a.date = :date " +
            ") AND t1.frequency IN (:#{#frequency.![name()]}) AND t1.section IN (:#{#section.![name()]})")
    List<TaskReportInfo> getTODOTasksForDate(@Param("date") LocalDate date,
                                        @Param("frequency") List<Frequency> frequency,
                                        @Param("section") List<Section> section);

    List<Task> getAllByFrequencyAndSection(Frequency frequency, Section section);


    /**
     * Get all tasks
     * @param date
     * @param frequency List of different frequencies you want to include in a search.
     * @param section List of different sections you want to include in a search.
     */
    @Query("SELECT new com.example.demo.sweeping.model.TaskReportInfo(t.id,t.name,t.frequency,t.section,a.id,a.date,a.time) FROM Task t " +
            "LEFT JOIN Activity a ON t.id = a.task.id " +
            "WHERE t.frequency IN (:#{#frequency.![name()]}) AND t.section IN (:#{#section.![name()]}) AND (a.date = :date OR a.date IS NULL) " +
            "ORDER BY a.date,a.time ASC")
    List<TaskReportInfo> getTasksForDate(@Param("date") LocalDate date,
                                  @Param("frequency") List<Frequency> frequency,
                                  @Param("section") List<Section> section);
}