package comf.farkalit.student.payload;

public class StudentSummary {

	private Long studId;
    private String username;
    private String name;

    public StudentSummary(Long studId, String username, String name) {
        this.studId = studId;
        this.username = username;
        this.name = name;
    }

	public Long getStudId() {
		return studId;
	}

	public void setStudId(Long studId) {
		this.studId = studId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
