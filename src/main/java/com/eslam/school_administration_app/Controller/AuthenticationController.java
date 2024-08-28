package com.eslam.school_administration_app.Controller;

import com.eslam.school_administration_app.Entity.Student;
import com.eslam.school_administration_app.services.AuthenticationService;
import com.eslam.school_administration_app.services.StudentService;
import com.eslam.school_administration_app.utils.LoginRequest;
import com.eslam.school_administration_app.utils.RegisterRequest;
import com.eslam.school_administration_app.utils.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authService.login(request));
    }
}
