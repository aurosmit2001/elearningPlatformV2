package com.example.eLearningFinal.repositories;

import com.example.eLearningFinal.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
