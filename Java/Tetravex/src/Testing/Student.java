package Testing;
public class Student
{
	int age;
	String name;
	int id;
	String major;
	public Student(String s,String m,int Age,int Id)
	{
		age=Age;
		id=Id;
		name=s;
		major=m;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int n)
	{
		age=n;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String s)
	{
		name=s;
	}
	public String toString()
	{
		return "Name :"+name+", age :"+age+",id :"+id+", major :"+major;
	}
	public int getId()
	{
		return id;
	}
	public static String get_name(Student s)
	{
		return s.name;
	}
}