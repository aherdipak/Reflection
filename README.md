# Reflection
- How do I get a field using reflection?

### \Temp.java
```

class Temp{
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




