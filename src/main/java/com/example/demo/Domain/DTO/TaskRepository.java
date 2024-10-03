package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Procedure("sp_get_tasks_between_dates")
    List<Task> getDoneTasksBetweenDates(@Param("dateStart") LocalDate dateStart,
                                        @Param("dateEnd") LocalDate dateEnd,
                                        @Param("frequency") String frequency,
                                        @Param("section") String section);

    @Procedure("sp_get_todo_tasks_between_dates")
    List<Task> getTODOTasksBetweenDates(@Param("dateStart") LocalDate dateStart,
                                        @Param("dateEnd") LocalDate dateEnd,
                                        @Param("frequency") String frequency,
                                        @Param("section") String section);
}