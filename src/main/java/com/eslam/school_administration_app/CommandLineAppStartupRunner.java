package com.eslam.school_administration_app;

import com.eslam.school_administration_app.Entity.Role;
import com.eslam.school_administration_app.Entity.User;
import com.eslam.school_administration_app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// !!!! THIS IS FOR TEST PURPOSES ONLY !!
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String...args) throws Exception {
		var admin= User.builder()
				.firstname("admin")
				.lastname("admin")
				.email("admin@email.com")
				.password(passwordEncoder.encode("admin"))
				.role(Role.ROLE_ADMIN).build();
		userRepository.save(admin);
	}
}
