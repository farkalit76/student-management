/**
 * 
 */
package comf.farkalit.student.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comf.farkalit.student.dto.Student;
import comf.farkalit.student.payload.StudentSummary;
import comf.farkalit.student.payload.UserIdentityAvailability;
import comf.farkalit.student.repository.StudentRepository;
import comf.farkalit.student.security.CurrentUser;
import comf.farkalit.student.security.StudentPrincipal;
import comf.farkalit.student.service.StudentService;

/**
 * @File name: StudentController.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/student/all", produces = "application/json")
	public List<Student> getAll() {
		System.out.println("get all students...");
		return studentService.getAll();
	}

	@GetMapping("/student/self")
	@PreAuthorize("hasRole('USER')")
	public StudentSummary getCurrentStudent(@CurrentUser StudentPrincipal currentUser) {
		
		System.out.println("get current students...");
		StudentSummary userSummary = new StudentSummary(currentUser.getStudId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/student/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		System.out.println("checkUsernameAvailability students...");
		Boolean isAvailable = !studentRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/student/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		System.out.println("checkEmailAvailability students...");
		Boolean isAvailable = !studentRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

}
