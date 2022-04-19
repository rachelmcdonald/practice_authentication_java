package com.practice.example.practice.repositories;

import com.practice.example.practice.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserAuthId(String authId);
}
