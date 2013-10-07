package ACMDIM;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.TreeSet;

public class StoneGameII {

	static StreamTokenizer st;
	static HashMap<Integer, Integer> count;
	static TreeSet<Integer> set;
	static int[]grund=new int[100001];
	static int cur_a=0;
	static int cur_b=0;
	private static int r() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	public static void grundyNum(int n)
	{
		MagicDS md=new MagicDS();
		for(int i=0;i<=n;i++)
		{
			final int a=i-i/2;
			final int b=i;
			
			while(cur_a<a)
			{
				md.remove(grund[cur_a]);
				cur_a++;
			}
			while(cur_b<b)
			{
				md.add(grund[cur_b]);
				cur_b++;
			}
			//invariant: Tree set always contains numbers from [a,b)
			grund[i]=md.getMin();
		}
	}
	public static void main(String[] args) throws Exception {
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		grundyNum(100000);
		int k = r();
		int res = 0;
		while (k-- > 0)
			res = res ^ grund[r()];
		if (res == 0)
			System.out.println("Second wins.");
		else
			System.out.println("First wins.");
	}
}
class MagicDS
{
	TreeSet<Integer> set=new TreeSet<Integer>();
	int[]count=new int[100001];
	public MagicDS()
	{
		for(int i=0;i<=100000;i++)
			set.add(i);
	}
	public void remove(int x)
	{
		count[x]--;
		if(count[x]==0)
			set.add(x);
	}
	public void add(int x)
	{
		count[x]++;
		if(count[x]==1)
			set.remove(x);
	}
	public int getMin()
	{
		return set.first();
	}
}
