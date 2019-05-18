/**
 * 
 */
package comf.farkalit.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import comf.farkalit.student.dto.Role;
import comf.farkalit.student.enums.RoleName;

/**
 * @File name: RoleRepository.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(RoleName roleName);
}
