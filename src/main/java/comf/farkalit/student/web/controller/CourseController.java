/**
 * 
 */
package comf.farkalit.student.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import comf.farkalit.student.dto.Courses;
import comf.farkalit.student.payload.ApiResponse;
import comf.farkalit.student.payload.CourseRequest;
import comf.farkalit.student.repository.StudentRepository;
import comf.farkalit.student.security.CurrentUser;
import comf.farkalit.student.security.StudentPrincipal;
import comf.farkalit.student.service.CourseService;

/**
 * @File name: CourseController.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	private CourseService courseService;
	
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/view", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	public List<Courses> viewAll(@CurrentUser StudentPrincipal currentUser) {
		System.out.println("view all courses for currentUser:"+currentUser.toString());
		return courseService.viewAll(currentUser);
	}

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createPoll(@Valid @RequestBody CourseRequest courseRequest) {
		System.out.println("create a course");
		Courses course = courseService.createCourse(courseRequest);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(course.getCourseId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Course Created Successfully"));
	}
}
