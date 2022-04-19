package com.practice.example.practice.components;

import com.practice.example.practice.models.Task;
import com.practice.example.practice.models.User;
import com.practice.example.practice.repositories.TaskRepository;
import com.practice.example.practice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public DataLoader() {

    }

    public void run(ApplicationArguments args) {
        User user1 = new User("auth0|625d433147a7f7006f3d12b4");
        userRepository.save(user1);

        User user2 = new User("auth0|625d60ed379bfd006f275c70");
        userRepository.save(user2);

        Task task1 = new Task("Do your shopping", "Go to Tesco to do the weekly food shop", user1);
        taskRepository.save(task1);
        Task task2 = new Task("Walk the dog", "take the dog to the park", user2);
        taskRepository.save(task2);
    }

}
