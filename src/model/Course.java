package model;

public class Course {

	private String header;
	private String section;
	private String instructor;
	public String accepted;
	public String enrolled;
	private boolean isfull;

	public void setHeader(String header) {
		this.header = header.replaceAll("\u00a0", " ");
	}

	public void setSection(String section) {
		this.section = section;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public void setAcceptedStudent(String accepted) {
		this.accepted = accepted;
		
	}

	public void setEnrolledStudent(String enrolled) {
		this.enrolled = enrolled;
	}

	@Override
	public String toString() {
		return "[ " + this.header.substring(0,header.indexOf("Lecture")).trim() + " / Section : " + this.section + " ] Enrolled/Accepted : " + enrolled + " / " + accepted + "ISFULL -> " + isfull;
	}

	public void isFull(boolean isfull) {
		this.isfull = isfull;
	}
}
