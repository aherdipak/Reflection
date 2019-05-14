
# Reflection
- How do I copy a field value of one object into another object using reflection?

### \User_2.java
```
class User_2{
	private String userName;
	private int userAge;
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
	@Override
	public String toString() {
		return "userName=" + userName + ", userAge=" + userAge ;
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

### \Test2.java
```

public class Test2 {

	// CONVERT FILED VALUE DATATYPE
	public Object convertDataTypeOfFiledValue(Field field,Object value) {
		//System.out.println(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1));
		if(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1).equalsIgnoreCase("String")) {
			return String.valueOf(value);
		}else if(field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1).equalsIgnoreCase("Integer")|| field.getType().getName().substring(field.getType().getName().lastIndexOf(".")+1).equalsIgnoreCase("int")) {
			return (int)value;
		}
		return null;
	}
	
	public void convertMasterToTemp(Object master,Object temp) throws Exception{
		Map<String,Object> masterValMap =new HashMap<>();
		try {
			Class mclass = master.getClass();
			Field [] mFields = mclass.getDeclaredFields();
			
			for (Field mField : mFields) {
				mField.setAccessible(true);
				if(mField.get(master)!= null)
					masterValMap.put(mField.getName(), mField.get(master)); // fieldName = FiledValue
			}
			
			Class tclass = temp.getClass();
			Field [] tFields = tclass.getDeclaredFields();
			
			for (Field tField : tFields) {
				tField.setAccessible(true);
				if(masterValMap.get(tField.getName())!= null) {
					Object convertedVal = convertDataTypeOfFiledValue(tField,masterValMap.get(tField.getName()));
					if(convertedVal !=null) {
						tField.set(temp, convertedVal);
					}
				}
					
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void printFiledAndValue(Object obj) throws Exception{
		Class clazz = obj.getClass();
		 Field[] fields = clazz.getDeclaredFields();
		    for(Field field : fields)
		    {
		        field.setAccessible(true);
		        System.out.println("Filed Name: "+field.getName() +"  --> "+field.get(obj));
		        if (!field.getType().isPrimitive() && !field.getType().getName().contains("java.lang"))
		        {
		        	printFiledAndValue(field.get(obj)); // calling same method
		        }
		    }
	}
	
	public static void main(String[] args) throws Exception {
		User_1 u1=new User_1("deepak!",25,"AAA");
		User_2 u2=new User_2();
		
		System.out.println("------- BEFORE ---------");
		
		System.out.println("U1: "+u1);
		System.out.println("U2: "+u2);
		
		new Test2(). convertMasterToTemp(u1,u2);
		
		System.out.println("------- AFTER ---------");
		
		System.out.println("U1: "+u1);
		System.out.println("U2: "+u2);
		
	}
}

```

### OUTPUT :
```
------- BEFORE ---------
U1: userName=deepak!, userAge=25, fname=AAA
U2: userName=null, userAge=0
------- AFTER ---------
U1: userName=deepak!, userAge=25, fname=AAA
U2: userName=deepak!, userAge=25

```

