# Reflection
- How do I get a field and it's value using reflection?

### \TestClass.java
```


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
```


### \Human.java
```
class Human{
	private String humanName;
	private User_1 user;
	public String getHumanName() {
		return humanName;
	}
	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
	public User_1 getUser() {
		return user;
	}
	public void setUser(User_1 user) {
		this.user = user;
	}
}
```


### \User_1.java
```

class User_1{
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
	public User_1(String userName,int userAge,String fname) {
		this.userName=userName;
		this.userAge=userAge;
		this.fname = fname;
	}
	public User_1() {}
	@Override
	public String toString() {
		return "userName=" + userName + ", userAge=" + userAge +", fname=" + fname ;
	}
	
}
```

### \Test.java
```
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
		
		User_1 u1=new User_1("BHARAT",25,"AAA");
		
		Human human = new Human();
		human.setHumanName("KARAN");
		human.setUser(u1);
		
		TestClass testClass = new TestClass();
		testClass.setHuman(human);
		
		new Test().printFiledAndValue(testClass);
		
	}
}
```

### OUTPUT :
```
Filed Name: human  --> com.dac.osms.test.Human@42a57993
Filed Name: humanName  --> KARAN
Filed Name: user  --> userName=BHARAT, userAge=25, fname=AAA
Filed Name: userName  --> BHARAT
Filed Name: userAge  --> 25
Filed Name: fname  --> AAA
Filed Name: byteVar  --> 5
Filed Name: shortVar  --> 20
Filed Name: intVar  --> 30
Filed Name: longVar  --> 60
Filed Name: floatVar  --> 20.0
Filed Name: doubleVar  --> 20.123
Filed Name: booleanVar  --> true
Filed Name: charVar  --> W
Filed Name: str  --> string
```

