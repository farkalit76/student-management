/**
 * 
 */
package comf.farkalit.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comf.farkalit.student.dto.Courses;

/**
 * @File name: CourseRepository.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

}
