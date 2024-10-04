package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t " +
           "JOIN Activity a ON t.id = a.task.id " +
           "WHERE t.frequency = :frequency AND t.section = :section AND (a.date BETWEEN :dateStart AND :dateEnd)")
    List<Task> getDoneTasksBetweenDates(@Param("dateStart") LocalDate dateStart,
                                    @Param("dateEnd") LocalDate dateEnd,
                                    @Param("frequency") String frequency,
                                    @Param("section") String section);

    @Query("SELECT t1 FROM Task t1 " +
            "WHERE t1.id NOT IN (" +
                "SELECT t.id FROM Task t " +
                "JOIN Activity a ON t.id = a.task.id " +
                "WHERE a.date BETWEEN :dateStart AND :dateEnd" +
            ") AND t1.frequency = :frequency AND t1.section = :section")
    List<Task> getTODOTasksBetweenDates(@Param("dateStart") LocalDate dateStart,
                                        @Param("dateEnd") LocalDate dateEnd,
                                        @Param("frequency") String frequency,
                                        @Param("section") String section);
}