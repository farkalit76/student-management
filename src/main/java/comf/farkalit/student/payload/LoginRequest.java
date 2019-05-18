/**
 * 
 */
package comf.farkalit.student.payload;

import javax.validation.constraints.NotBlank;

/**
 * @File name: LoginRequest.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
public class LoginRequest {

	@NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [usernameOrEmail=" + usernameOrEmail + "]";
	}
    
}
