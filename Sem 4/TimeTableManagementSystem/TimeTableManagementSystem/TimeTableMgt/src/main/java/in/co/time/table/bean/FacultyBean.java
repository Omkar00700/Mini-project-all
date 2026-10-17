package in.co.time.table.bean;

public class FacultyBean implements DropdownListBean ,Comparable<FacultyBean>{

	
	private long id;
	private String facultyName;

	private String department;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return facultyName;
	}

	public int compareTo(FacultyBean next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}

	
	
	
	
}
