package in.co.time.table.bean;

public class SubjectBean implements DropdownListBean,Comparable<SubjectBean>{
	
	
	private long id;
	private String subjectName;
	private long subjectCode;

	
	
	public long getId() {
		return id;
	}
	
	
	
	public void setId(long id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public long getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(long subjectCode) {
		this.subjectCode = subjectCode;
	}



	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}



	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}




	public int compareTo(SubjectBean next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}
	
	
	

}
