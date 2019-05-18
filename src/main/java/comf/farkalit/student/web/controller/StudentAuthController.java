/**
 * 
 */
package comf.farkalit.student.web.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import comf.farkalit.student.dto.Role;
import comf.farkalit.student.dto.Student;
import comf.farkalit.student.enums.RoleName;
import comf.farkalit.student.exception.AppException;
import comf.farkalit.student.payload.ApiResponse;
import comf.farkalit.student.payload.JwtAuthenticationResponse;
import comf.farkalit.student.payload.LoginRequest;
import comf.farkalit.student.payload.SignUpRequest;
import comf.farkalit.student.repository.RoleRepository;
import comf.farkalit.student.repository.StudentRepository;
import comf.farkalit.student.service.JwtTokenProvider;

/**
 * @File name: AuthController.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@RestController
@RequestMapping("/api/auth")
public class StudentAuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println("authenticateUser .."+loginRequest.toString());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/student/signup")
	public ResponseEntity<?> registerStudent(@Valid @RequestBody SignUpRequest signUpRequest) {
		
		System.out.println("SignUpRequest .."+signUpRequest.toString());		
		
		if (studentRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (studentRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating student's account
		Student student = new Student(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

		student.setPassword(passwordEncoder.encode(student.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("Student Role not set."));

		student.setRoles(Collections.singleton(userRole));

		Student result = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User {"+result.getUsername()+"} registered successfully") );
	}
}
