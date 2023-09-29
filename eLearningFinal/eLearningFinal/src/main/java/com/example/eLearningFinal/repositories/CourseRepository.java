package com.example.eLearningFinal.repositories;

import com.example.eLearningFinal.model.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Course> findAll(Specification<Course> specification, Sort courseSort);
}
