package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByTodoId(Long id);
}
