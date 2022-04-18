package com.practice.example.practice.components;

import com.practice.example.practice.models.Task;
import com.practice.example.practice.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    TaskRepository taskRepository;

    public DataLoader() {

    }

    public void run(ApplicationArguments args) {
        Task task1 = new Task("Do your shopping", "Go to Tesco to do the weekly food shop");
        taskRepository.save(task1);
        Task task2 = new Task("Walk the dog", "take the dog to the park");
        taskRepository.save(task2);
    }

}
