package com.example.school_administration_app.Repositories;

import com.example.school_administration_app.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
