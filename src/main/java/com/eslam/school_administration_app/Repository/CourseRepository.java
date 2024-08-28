package com.eslam.school_administration_app.Repository;

import com.eslam.school_administration_app.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
