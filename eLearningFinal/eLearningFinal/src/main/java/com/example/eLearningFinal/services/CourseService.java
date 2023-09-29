package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.repositories.CourseRepository;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Course> getALLCourse() {
        return courseRepository.findAll();
    }


    public Course getCourseById(Long course_id) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        return  courseOptional.orElse(null);
    }

    public Course createCourse(Course course, String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getRole().equals("Instructor"))
            return courseRepository.save(course);
        return null;
    }


    public Course updateCourse(Long course_id, String username, Course updatedCourse) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        User user = userRepository.findByUsername(username);
        if (courseOptional.isPresent() && user != null && user.getRole().equals("Instructor")) {
            Course course = courseOptional.get();
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

    public void deleteCourse(Long courseId, String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getRole().equals("Instructor")) {
            courseRepository.deleteById(courseId);
        }
    }

    public List<Course> searchCourses(
            String keyword,
            Long categoryId,
            String sortField,
            String sortOrder) {

        Specification<Course> specification = Specification.where(null);

        // Add filtering conditions based on keyword and categoryId
        if (keyword != null && !keyword.isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.or(
                            builder.like(root.get("coursename"), "%" + keyword + "%"),
                            builder.like(root.get("instructorname"), "%" + keyword + "%")
                            // Add more fields to search as needed
                    )
            );
        }

        if (categoryId != null) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("category").get("id"), categoryId)
            );
        }

        // Add sorting logic
        Sort courseSort = Sort.unsorted();
        if (sortField != null && !sortField.isEmpty()) {
            if ("coursename".equalsIgnoreCase(sortField)) {
                courseSort = Sort.by("coursename");
            } else if ("enrolleddate".equalsIgnoreCase(sortField)) {
                courseSort = Sort.by("enrolleddate");
            }

        }

        // Execute the query with specifications and sorting
        return courseRepository.findAll(specification, courseSort);
    }
}
