package Testing;
public class person
{
	private int age;
	private String name;
	public person()
	{
		
	}
	public person(String s,int n)
	{
		name=s;
		age=n;
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
		return "Name :"+name+", age :"+age;
	}
	public static String get_name(person p)
	{
		return p.name;
	}
	public static void main(String[]args)
	{
		person loai =new person("Loai",18);
		person Bassam =new person("Bassam",20);
		person Ahmed=new person("Ahmed",19);
		loai.setName("Loai Ghoraba");
		Bassam.setAge(19);
		System.out.println(loai+"\n"+Bassam+"\n"+Ahmed);
	}
}