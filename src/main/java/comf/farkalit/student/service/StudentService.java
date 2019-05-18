/**
 * 
 */
package comf.farkalit.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comf.farkalit.student.dto.Student;
import comf.farkalit.student.repository.StudentRepository;

/**
 * @File name: StudentService.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
}
