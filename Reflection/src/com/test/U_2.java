package com.test;

public class U_2 {

	private String userName;
	private int userAge;
	
	private String fname;
	private String lname;
	
	
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	public U_2(String userName,int userAge,String fname) {
		this.userName=userName;
		this.userAge=userAge;
		this.fname = fname;
	}
	
	public U_2(String userName,int userAge,String fname,String lname) {
		this.userName=userName;
		this.userAge=userAge;
		this.fname = fname;
		this.lname = lname;
	}
	public U_2() {}
	
	@Override
	public String toString() {
		return "userName=" + userName + ", userAge=" + userAge ;
	}

}
