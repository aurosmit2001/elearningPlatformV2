package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.UserCourse;
import com.example.eLearningFinal.services.CourseService;
import com.example.eLearningFinal.services.LessonService;
import com.example.eLearningFinal.services.UserCourseService;
import com.example.eLearningFinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollement")
public class EnrollementController {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private LessonService lessonService;


    @PostMapping("/enroll")
    public ResponseEntity<String> enrollUserInCourse(
            @RequestParam Long user_id,
            @RequestParam Long course_id) {
        try {
            userCourseService.enrollUserInCourse(user_id, course_id);
            return ResponseEntity.ok("User enrolled in the course.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to enroll user in the course.");
        }
    }
    @PostMapping("/unenroll")
    public ResponseEntity<String> unenrollUserFromCourse(
            @RequestParam Long user_id,
            @RequestParam Long course_id) {
        try {
            userCourseService.unenrollUserFromCourse(user_id, course_id);
            return ResponseEntity.ok("User unenrolled from the course.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to unenroll user from the course.");
        }
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Course>> getEnrolledCourses(@PathVariable Long user_id) {
        try {
            List<Course> enrolledCourses = userCourseService.getUserEnrolledCourses(user_id);
            return ResponseEntity.ok(enrolledCourses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
