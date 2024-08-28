package com.example.school_administration_app.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor @Getter @Setter @ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Course name can't be empty")
    @Size(min = 3, max = 150, message = "Course name should be between 3 and 150 characters")    @Column(name = "name")
    private String courseName;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonBackReference
    Set<Student> students;

    public Course(long id, String courseName, String description) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
    }

}
