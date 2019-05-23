/**
 * 
 */
package comf.farkalit.student.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import comf.farkalit.student.dto.Student;

/**
 * @File name: StudentPrincipal.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
public class StudentPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    @NotEmpty
    @JsonProperty("studId")
	private Long studId;

	@NotNull
    @NotEmpty
    @JsonProperty("name")
	private String name;

	@NotNull
    @NotEmpty
    @JsonProperty("username")
	private String username;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public StudentPrincipal(Long studId, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.studId = studId;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities=authorities;
	}
	
	public static StudentPrincipal create(Student user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new StudentPrincipal(
                user.getStudId(),
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getStudId() {
        return studId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPrincipal that = (StudentPrincipal) o;
        return Objects.equals(studId, that.studId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studId);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentPrincipal [studId=" + studId + ", name=" + name + ", username=" + username + ", email=" + email + ", authorities=" + authorities + "]";
	}


}
