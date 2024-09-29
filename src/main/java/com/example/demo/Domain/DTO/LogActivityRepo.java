package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.AppActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogActivityRepo extends JpaRepository<AppActivityLog, Integer> {
}