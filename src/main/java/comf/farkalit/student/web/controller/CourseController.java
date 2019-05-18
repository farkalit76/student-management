/**
 * 
 */
package comf.farkalit.student.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import comf.farkalit.student.dto.Courses;
import comf.farkalit.student.service.CourseService;

/**
 * @File name: CourseController.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping( method = RequestMethod.GET, value = "/all",produces = "application/json" )
	public List<Courses> getAll(){
	
		System.out.println("get all courses");
		return courseService.getAll();
	}
}
