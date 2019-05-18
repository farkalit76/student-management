/**
 * 
 */
package comf.farkalit.student.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comf.farkalit.student.dto.Student;
import comf.farkalit.student.repository.StudentRepository;
import comf.farkalit.student.security.StudentPrincipal;

/**
 * @File name: CustomStudentDetailsService.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@Service
public class CustomStudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// Let people login with either username or email
		Student student = studentRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Student User not found with username or email : " + usernameOrEmail));

		return StudentPrincipal.create(student);
	}

	// This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserByStudId(Long studId) {
    	Student student = studentRepository.findById(studId).orElseThrow(
            () -> new UsernameNotFoundException("Student User not found with studId : " + studId)
        );

        return StudentPrincipal.create(student);
    }
}
