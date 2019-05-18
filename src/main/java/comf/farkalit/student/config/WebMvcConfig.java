/**
 * 
 */
package comf.farkalit.student.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @File name: WebMvcConfig.java This class .....
 *
 * @author name: Farkalit Usman (FarkalitUsman)
 * @Created on: 18 May 2019
 */
public class WebMvcConfig implements WebMvcConfigurer {

	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("WebMvcConfig...addCorsMappings....");
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
			.maxAge(MAX_AGE_SECS);
	}

}
