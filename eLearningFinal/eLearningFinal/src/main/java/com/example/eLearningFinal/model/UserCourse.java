package com.example.eLearningFinal.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName ="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @ElementCollection
    private List<Long> completedLessonIds;

    public List<Long> getCompletedLessonIds() {
        return completedLessonIds;
    }

    public void setCompletedLessonIds(List<Long> completedLessonIds) {
        this.completedLessonIds = completedLessonIds;
    }

    public UserCourse(List<Long> completedLessonIds) {
        this.completedLessonIds = completedLessonIds;
    }

    private Date enrollmentDate;
    private boolean completed;
    private double progress;


    public UserCourse() {
    }



    public UserCourse(Long id, User user, Course course, Date enrollmentDate, boolean completed, double progress) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.completed = completed;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
