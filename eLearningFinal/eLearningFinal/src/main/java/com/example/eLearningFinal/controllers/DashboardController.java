package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.model.UserCourse;
import com.example.eLearningFinal.services.CourseService;
import com.example.eLearningFinal.services.UserCourseService;
import com.example.eLearningFinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

@PostMapping("/cpmplete-lesson")
public ResponseEntity<UserCourse> markLessonCompleted(
        @RequestParam Long userCourseId,
        @RequestParam Long completedLessonId) {
    // Retrieve the UserCourse entity based on userCourseId
    UserCourse userCourse = userCourseService.markLessonCompleted(userCourseId, completedLessonId);

    if (userCourse != null) {
        return new ResponseEntity<>(userCourse, HttpStatus.OK);
    } else {
        // Handle error: UserCourse not found or lesson not part of the course
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
@GetMapping("/course-progress/{userCourseId}")
public ResponseEntity<Integer> getCourseProgress(@PathVariable Long userCourseId) {
    int progress = userCourseService.calculateCourseProgress(userCourseId);
    if (progress >= 0) {
        return new ResponseEntity<>(progress, HttpStatus.OK);
    } else {
        // Handle error: UserCourse not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @GetMapping("/course-completed/{userId}/{courseId}")
    public ResponseEntity<Boolean> isCourseCompleted(
            @PathVariable Long user_id,
            @PathVariable Long course_id) {
        // Retrieve the User and Course entities based on userId and courseId

        User user = userService.getUserById(user_id); // Implement userService to fetch a user by ID
        Course course = courseService.getCourseById(course_id); // Implement courseService to fetch a course by ID

        if (user == null || course == null) {
            // Handle error: User or Course not found
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

        boolean courseCompleted = userCourseService.isCourseCompleted(user, course);
        return new ResponseEntity<>(courseCompleted, HttpStatus.OK);
    }

}
