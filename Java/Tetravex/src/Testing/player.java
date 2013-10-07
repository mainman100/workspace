package Testing;
import java.util.Arrays;
import java.util.PriorityQueue;
public class player implements Comparable<player>
{
	String name;
	int time;
	public player(String s,int n)
	{
		name=s;
		time=n;
	}
	public int compareTo(player p)
	{
		if(time<p.time)
			return -1;
		if(time>p.time)
			return 1;
		return 0;
	}
	public String toString()
	{
		return "("+name+","+time+")";
	}
	public static void main(String[]args)
	{
		PriorityQueue<player> q=new PriorityQueue<player>();
		player loai=new player("loai",20);
		player ali=new player("ali",10);
		player hashem=new player("hashem",30);
		player ahmed=new player("ahmed",5);
		q.add(loai);
		q.add(ahmed);
		q.add(hashem);
		q.add(ali);
		int n=q.size();
		for(int i=0;i<n;i++)
		{
			System.out.println(q.poll());
		}

	}
}