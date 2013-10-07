package Testing;
public class employee extends person
{
	String job;
	public employee(String j,String s,int a)
	{
		job=j;
	}
	public String getJob()
	{
		return job;
	}
	public String getName()
	{
		return super.getName()+" Adnan";
	}
	public String toString()
	{ 
		return super.toString()+" job :"+job;
	}
	public static void main(String[]args)
	{
		employee loai=new employee("TA","Loai",18);
		loai.setName("Loai Ghoraba");
		int age=loai.getAge();
		System.out.println(loai);
		String name=person.get_name(loai);
		int x=5;
		person bassam=new employee("Mecha","Bassam",19);
		System.out.println(bassam.getName());
	}
}