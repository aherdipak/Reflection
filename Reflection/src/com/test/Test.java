package com.test;

import java.lang.reflect.Field;


public class Test {

	public void printFiledAndValue(Object obj) throws Exception{
		Class clazz = obj.getClass();
		 Field[] fields = clazz.getDeclaredFields();
		    for(Field field : fields)
		    {
		        field.setAccessible(true);
		        System.out.println("Filed Name: "+field.getName() +"  --> "+field.get(obj));
		        if (!field.getType().isPrimitive() && !field.getType().getName().contains("java.lang"))
		        {
		        	printFiledAndValue(field.get(obj));
		        }
		    }
	}
	
	public static void main(String[] args) throws Exception {
		
		User u1=new User("BHARAT",25,"AAA");
		
		Human human = new Human();
		human.setHumanName("KARAN");
		human.setUser(u1);
		
		TestClass testClass = new TestClass();
		testClass.setHuman(human);
		
		new Test().printFiledAndValue(testClass);
		
	}
}




class TestClass{
	Human human;
	byte byteVar = 5;
    short shortVar = 20;
    int intVar = 30;
    long longVar = 60;
    float floatVar = 20;
    double doubleVar = 20.123;
    boolean booleanVar = true;
    char charVar ='W';
    String str = "string";
    
	public Human getHuman() {
		return human;
	}
	public void setHuman(Human human) {
		this.human = human;
	}
   
}

class Human{
	private String humanName;
	private User user;
	public String getHumanName() {
		return humanName;
	}
	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

class User{
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
	public User(String userName,int userAge,String fname) {
		this.userName=userName;
		this.userAge=userAge;
		this.fname = fname;
	}
	public User() {}
	@Override
	public String toString() {
		return "userName=" + userName + ", userAge=" + userAge +", fname=" + fname ;
	}
	
}
