package com.example.labdatabase;

public class Student {
	private int idNumber; // Automatically set by DatabaseHelper.
	private String fName;
	private String lName;

	static final String TABLE_NAME = "enrolment";
	static final String COLUMN_IDNUMBER = "_idNumber";
	static final String COLUMN_FNAME = "fName";
	static final String COLUMN_LNAME = "lName";
	
	public Student(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	
	public int getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}
	
	public String getfName() {
		return fName;
	}
	
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
}
