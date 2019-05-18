/**
 * 
 */
package comf.farkalit.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comf.farkalit.student.dto.Student;

/**
 * @File name: StudentRepository.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByEmail(String email);

    Optional<Student> findByUsernameOrEmail(String username, String email);

    List<Student> findByStudId(List<Long> userIds);

    Optional<Student> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
