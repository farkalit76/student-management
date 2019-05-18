/**
 * 
 */
package comf.farkalit.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comf.farkalit.student.dto.Courses;
import comf.farkalit.student.payload.CourseRequest;
import comf.farkalit.student.repository.CourseRepository;
import comf.farkalit.student.security.StudentPrincipal;

/**
 * @File name: CourseService.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public List<Courses> viewAll(StudentPrincipal currentUser){
		return courseRepository.findAll();
	}
	
	public Courses createCourse(CourseRequest courseRequest){
		Courses course = new Courses();
		
		course.setName(courseRequest.getName());
		course.setDescription(courseRequest.getDescription());
		
		return courseRepository.save(course);
	}
}
