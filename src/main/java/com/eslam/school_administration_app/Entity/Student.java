package com.eslam.school_administration_app.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "students")
@NoArgsConstructor @Getter @Setter @ToString
public class Student  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 3, max = 150, message = "Name should be between 3 and 150 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid e.g. ( user@email.com ) ")
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))

    @JsonBackReference
    Set<Course> courses;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    User user;
    public Student(String name, String email ){
        this.name=name;
        this.email = email;
    }

}
