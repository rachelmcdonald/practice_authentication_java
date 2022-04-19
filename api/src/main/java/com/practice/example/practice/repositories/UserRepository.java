package com.practice.example.practice.repositories;

import com.practice.example.practice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByAuthId(String authId);
}
