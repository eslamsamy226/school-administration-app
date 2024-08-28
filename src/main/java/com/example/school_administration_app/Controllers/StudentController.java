package com.example.school_administration_app.Controllers;

import com.example.school_administration_app.Entities.Course;
import com.example.school_administration_app.Entities.Student;
import com.example.school_administration_app.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws Exception {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new Exception("Student not found"));

        return ResponseEntity.ok(student);
    }



    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody @Valid Student studentDetails) throws Exception {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<Set<Course>> getCoursesByStudentId(@PathVariable Long id) throws Exception {
        Set<Course> courses = studentService.getCoursesByStudentId(id);
        return ResponseEntity.ok(courses);
    }
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Student> enrollStudentInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) throws Exception {
        Student student = studentService.enrollStudentInCourse(studentId, courseId);
        return ResponseEntity.ok(student);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handelValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMsg = error.getDefaultMessage();
            errors.put(fieldName,errorMsg);
        });
        return errors;
    }
}
