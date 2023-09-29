package com.example.eLearningFinal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long course_id;
    @Column(name = "title")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL)
    private List<Lesson> lessons;
//
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<UserCourse> enrolledUsers;


    public Course(Long course_id) {
        this.course_id = course_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Course() {
    }

    public Course(Long id, String title, String description, List<Lesson> lessons, List<UserCourse> enrolledUsers) {
//        this.id = id;
        this.title = title;
        this.description = description;
        this.lessons = lessons;
        this.enrolledUsers = enrolledUsers;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<UserCourse> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(List<UserCourse> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public void setCreator(User user) {
    }
}
