package Test;
public class student extends human
{
	int id;
	String major;
	student(String name,int age,String Major,int ID)
	{
		super(name,age);
		major=Major;
		id=ID;
	}
	public String toString()
	{
		return "name :"+name+", age :"+age+", major :"+major+", id :"+id;
	}
	public void talk()
	{
		System.out.println("I'm a student");
	}
	public void eftekasa()
	{
		System.out.println("lol");
	}
	public static void main(String[]args)
	{
		human abdo=new student("Abdo",19,"MET",856);
		//abdo.eftekasa();
		abdo.talk();
	}
}
