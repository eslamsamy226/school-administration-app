package com.eslam.school_administration_app.Repository;

import com.eslam.school_administration_app.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
