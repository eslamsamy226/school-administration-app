package com.example.school_administration_app.Repositories;

import com.example.school_administration_app.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
