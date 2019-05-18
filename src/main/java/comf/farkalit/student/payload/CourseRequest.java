package comf.farkalit.student.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CourseRequest {

	@NotBlank
    @Size(max = 45)
	private String name;
	
	@Size(max = 145)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
