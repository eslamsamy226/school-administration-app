package com.eslam.school_administration_app.services;

import com.eslam.school_administration_app.Entity.Course;
import com.eslam.school_administration_app.Entity.Student;
import com.eslam.school_administration_app.Repository.CourseRepository;
import com.eslam.school_administration_app.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository,CourseRepository courseRepository){

        this.studentRepository=studentRepository;
        this.courseRepository=courseRepository;
    }


    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }


    public List<Student> findStudentCourses(long student_id) {
        return null;
    }

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {
         studentRepository.save(student);
        return student;
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }


    public Set<Course> getCoursesByStudentId(Long studentId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("Student not found"));
        return student.getCourses();
    }
    public Student updateStudent(Long id, Student studentDetails) throws Exception {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());

        return studentRepository.save(student);
    }

    public Student enrollStudentInCourse(Long studentId, Long courseId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new Exception("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new Exception("Course not found"));
        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        return student;

    }
}
