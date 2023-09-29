package com.example.eLearningFinal.repositories;

import com.example.eLearningFinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByusername(String username);

    Optional<User> findById(Long userId);

    User findByUsername(String username);
}
