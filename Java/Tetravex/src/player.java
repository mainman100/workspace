import java.io.Serializable;
public class player implements Comparable<player>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9029858525762518951L;
	String name;
	int time;
	public player(String s,int n)
	{
		name=s;
		time=n;
	}
	public void setTime(int t)
	{
		time=t;
	}
	public int getTime()
	{
		return time;
	}
	public int compareTo(player p)
	{
		if(time<0)//very big
			return 1;
		if(p.time<0)//very small
			return -1;
		if(time<p.time)
			return -1;
		if(time>p.time)
			return 1;
		return 0;
	}
	public String toString()
	{
		String data="";
		String timeFormat=read.time(time);
		data+=name;
		for(int i=0;i<30-name.length();i++)
			data+='.';
		data+=timeFormat;
		return data;
	}
	public player clone()
	{
		return new player(name,time);
	}
	public static void main(String[]args)
	{
		player loai=new player("loai",100);
		player p=new player("somebody",-1);
		System.out.println(p.compareTo(loai)+"\n"+loai.compareTo(p));
	}
}