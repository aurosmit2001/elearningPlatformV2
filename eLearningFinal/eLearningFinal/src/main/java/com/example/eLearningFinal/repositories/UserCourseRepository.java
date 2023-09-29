package com.example.eLearningFinal.repositories;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {


    @Query(nativeQuery = true, value = "SELECT * FROM UserCourse where user_id = :userId and course_id= :courseId")
    List<UserCourse> findByUser_idAndCourse_id(@Param("userId") Long user_id, @Param("courseId") Long course_id);

    @Query(nativeQuery = true, value = "SELECT * FROM UserCourse where user_id = :userId ")
    List<UserCourse> findByUser_id(@Param("userId") Long user_id);

    boolean existsByUserAndCourse(User user, Course course);


//   List<UserCourse> findByUserId(Long userId);

   // List<UserCourse> findBYUserId(long userId);
    //List<UserCourse> findByIdAndUserIdAndCourseId(Long id, Long userId, Long courseId);

}
