/**
 * 
 */
package comf.farkalit.student.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import comf.farkalit.student.dto.Student;
import comf.farkalit.student.service.StudentService;

/**
 * @File name: StudentController.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/all", produces="application/json")
	public List<Student> getAll() {
		System.out.println("get all students...");
		return studentService.getAll();
	}

}
