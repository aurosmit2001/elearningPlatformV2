package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getALLCourse() {
        return courseService.getALLCourse();
    }
    @GetMapping("/{course_id}")
    public  Course getCourseById(@PathVariable Long course_id){
        return courseService.getCourseById(course_id);
    }
    @PostMapping
    public Course createCourse(@RequestBody Course course, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return courseService.createCourse(course, username);
    }
    @PutMapping("/{courseId}")
    public Course updateCourse(@PathVariable Long course_id, @RequestBody Course updatedCourse, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Course updated = courseService.updateCourse(course_id, username, updatedCourse);
        return courseService.updateCourse(course_id, username, updatedCourse);
    }

    @DeleteMapping("/{course_id}")
    public String deleteCourse(@PathVariable Long course_id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        courseService.deleteCourse(course_id, username);
        return "Course deleted successfully";
    }
    @GetMapping("/courses")
    public List<Course> searchCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortOrder) {
        return courseService.searchCourses(keyword, categoryId, sortField, sortOrder);
    }

}
