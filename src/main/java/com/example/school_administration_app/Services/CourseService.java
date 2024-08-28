package com.example.school_administration_app.Services;

import com.example.school_administration_app.Entities.Course;
import com.example.school_administration_app.Entities.Student;
import com.example.school_administration_app.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course courseDetails) throws Exception {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new Exception("Course not found"));

        course.setCourseName(courseDetails.getCourseName());
        course.setDescription(courseDetails.getDescription());
        return courseRepository.save(course);
    }

    public Set<Student> getStudentsByCourseId(Long courseId) throws Exception {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new Exception("Course not found"));

        return course.getStudents();
    }
}
