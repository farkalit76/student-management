/**
 * 
 */
package comf.farkalit.student.payload;

/**
 * @File name: JwtAuthenticationResponse.java
 * This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
public class JwtAuthenticationResponse {

	private String accessToken;
    
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
    
	
}
