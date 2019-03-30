
# Reflection
- How do I get a field name and value If Type Is Object with the annotation of javax.persistence.EmbeddedId(@EmbeddedId) using reflection?

### \Human.java
```
@Entity
public class Human {

	@EmbeddedId
	Student student;

	@Column
	private String color;

	@Column
	private int height;

	@Override
	public String toString() {
		return "student=" + student + ", color=" + color + ", height=" + height;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}

```


### \Student.java
```

@Embeddable
public class Student implements Serializable {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "name=" + name + ", age=" + age;
	}
}

```


### \Test3.java
```

public class Test3 {
	
	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setName("ABC");
		student.setAge(25);
		Human human = new Human();
		human.setColor("BLACK");
		human.setStudent(student);
		new Test().printObject(human);
	}
	
	public void printObject(Object obj) throws Exception {
		
		Class clazz = obj.getClass();
		Field[] outFileds = clazz.getDeclaredFields();
		
		for(int i=0;i<outFileds.length;i++) {
			outFileds[i].setAccessible(true);
			
			if(outFileds[i].getAnnotation(EmbeddedId.class)!=null) {
				Class inClazz = outFileds[i].getType();
				
				Field[] inFileds = inClazz.getDeclaredFields();
				
				for (int j = 0; j < inFileds.length; j++) {
					Field field = inFileds[j];
					field.setAccessible(true);
					
	               PropertyDescriptor  pd = new PropertyDescriptor(outFileds[i].getName(), obj.getClass());
	               Object  objchld = pd.getReadMethod().invoke(obj);
				   System.out.println("ASSOCIATED @EmbeddedId CLASS MEMBER: "+field.getName()+" --> "+field.get(objchld));
				}
				continue;
			}//EmbeddedId
			System.out.println("CLASS MEMBER: "+outFileds[i].getName()+" --> "+outFileds[i].get(obj));
		}
	}
	
}
```

### OUTPUT :
```
ASSOCIATED @EmbeddedId CLASS MEMBER: name --> ABC
ASSOCIATED @EmbeddedId CLASS MEMBER: age --> 25
CLASS MEMBER: color --> BLACK
CLASS MEMBER: height --> 0

```


