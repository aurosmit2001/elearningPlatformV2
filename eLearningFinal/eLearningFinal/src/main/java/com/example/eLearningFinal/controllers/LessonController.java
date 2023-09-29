package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Lesson;
import com.example.eLearningFinal.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons(@PathVariable Long courseId){
        return lessonService.getAllLessons(courseId);
    }
    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable Long courseId, @PathVariable Long lessonId) {
        return lessonService.getLessonById(courseId, lessonId);
    }
    @PostMapping
    public Lesson createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return lessonService.createLesson(courseId, lesson, username);
    }
    @PutMapping("/{lessonId}")
    public Lesson updateLesson(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            @RequestBody Lesson updatedLesson,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername();
        return lessonService.updateLesson(courseId, lessonId, updatedLesson, username);
    }
    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable Long courseId, @PathVariable Long lessonId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        lessonService.deleteLesson(courseId, lessonId, username);
    }

}
