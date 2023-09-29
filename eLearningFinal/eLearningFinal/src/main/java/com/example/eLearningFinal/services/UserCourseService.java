package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.model.UserCourse;
import com.example.eLearningFinal.repositories.CourseRepository;
import com.example.eLearningFinal.repositories.LessonRepository;
import com.example.eLearningFinal.repositories.UserCourseRepository;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private LessonRepository lessonRepository;



    public void enrollUserInCourse(Long user_id, Long course_id) {
        // Logic to enroll a user in a course
        UserCourse userCourse = new UserCourse();
        userCourse.setUser(new User(user_id)); // Create a User entity or fetch it from the repository
        userCourse.setCourse(new Course(course_id)); // Create a Course entity or fetch it from the repository
        userCourseRepository.save(userCourse);
    }


    public void unenrollUserFromCourse(Long user_id, Long course_id) {
        // Logic to unenroll a user from a course
        List<UserCourse> userCourse =  userCourseRepository.findByUser_idAndCourse_id(user_id, course_id);
        //List<UserCourse> userCourse =  userCourseRepository.findByUser_idAndCourse_id(user_id, course_id);
        if (userCourse != null) {
            userCourseRepository.delete((UserCourse) userCourse);
        }
    }

    public List<Course> getUserEnrolledCourses(Long user_id) {
        // Logic to fetch the courses enrolled by a user
        List<UserCourse> userCourses = userCourseRepository.findByUser_id(user_id);
        List<Course> enrolledCourses = new ArrayList<>();
        for (UserCourse userCourse : userCourses) {
            enrolledCourses.add(userCourse.getCourse());
        }
        return enrolledCourses;
    }



    public UserCourse markLessonCompleted(Long userCourseId, Long lessonIdToComplete) {
        UserCourse userCourse = userCourseRepository.findById(userCourseId).orElse(null);

        if (userCourse == null) {
            return null; // Handle error: UserCourse not found
        }
        Course course = userCourse.getCourse();

        // Check if the lesson exists in the course
        if (lessonExistsInCourse(course, lessonIdToComplete)) {
            List<Long> completedLessonIds = userCourse.getCompletedLessonIds();
            if (!completedLessonIds.contains(lessonIdToComplete)) {
                completedLessonIds.add(lessonIdToComplete);
            }
            int totalLessons = course.getLessons().size();
            int completedLessons = completedLessonIds.size();
            int progress = (completedLessons * 100) / totalLessons;

            userCourse.setProgress(progress);
            userCourse.setCompletedLessonIds(completedLessonIds);

            return userCourseRepository.save(userCourse);
        } else {
            return null; // Handle error: Lesson not found in the course
        }
    }
    public int calculateCourseProgress(Long userCourseId) {
        UserCourse userCourse = userCourseRepository.findById(userCourseId).orElse(null);

        if (userCourse == null) {
            return -1; // Handle error: UserCourse not found
        }

        int totalLessons = userCourse.getCourse().getLessons().size();
        int completedLessons = userCourse.getCompletedLessonIds().size();

        if (totalLessons == 0) {
            return 0; // Handle division by zero
        }

        return (completedLessons * 100) / totalLessons;
    }
    private boolean lessonExistsInCourse(Course course, Long lessonId) {
        return course.getLessons().stream().anyMatch(lesson -> lesson.getId().equals(lessonId));
    }


    public boolean isCourseCompleted(User user, Course course) {

        return userCourseRepository.existsByUserAndCourse(user, course);
    }
}
