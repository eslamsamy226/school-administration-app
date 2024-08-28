package com.eslam.school_administration_app.services;

import com.eslam.school_administration_app.Entity.Student;
import com.eslam.school_administration_app.Repository.UserRepository;
import com.eslam.school_administration_app.Entity.Role;
import com.eslam.school_administration_app.Entity.User;
import com.eslam.school_administration_app.utils.AuthenticationResponse;
import com.eslam.school_administration_app.utils.LoginRequest;
import com.eslam.school_administration_app.utils.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final StudentService studentService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_STUDENT).build();
        User user1=repository.save(user);
        Student student=new Student(request.getFirstname()+" "+request.getLastname(),request.getEmail());
        student.setUser(user1);
        studentService.saveStudent(student);

        String jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jtwToken).build();
    }


    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jtwToken).build();
    }
}
