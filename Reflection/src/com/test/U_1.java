package com.test;

public class U_1 {

	private String userName;
	private int userAge;
	private String fname;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public U_1(String userName,int userAge,String fname) {
		this.userName=userName;
		this.userAge=userAge;
		this.fname = fname;
	}
	public U_1() {}
	@Override
	public String toString() {
		return "userName=" + userName + ", userAge=" + userAge +", fname=" + fname ;
	}
	

}
