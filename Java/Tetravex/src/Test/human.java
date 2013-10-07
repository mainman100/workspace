package Test;
public class human
{
	String name;
	int age;
	public human()
	{
		
	}
	public human(String s,int a)
	{
		name=s;
		age=a;
	}
	public void talk()
	{
		System.out.println("I'm a human");
	}
	public String toString()
	{
		return "name :"+name+", age :"+age;
	}
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
	private void test()
	{
		System.out.println("A");
	}
	public static void main(String[]args)
	{
		human h=new student("",1,"",1);
		h.test();
	}
}
