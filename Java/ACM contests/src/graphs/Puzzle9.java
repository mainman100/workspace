package graphs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Puzzle9 
{
	static StreamTokenizer st;
	private static int nextInt() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		HashMap<Integer,String> sol=new HashMap<Integer, String>();
		Queue<node> q=new LinkedList<node>();
		node start=new node(123456789,0,"");
		q.add(start);
		while(!q.isEmpty())
		{
			node cur=q.poll();
			int index=cur.index;
			String vis=sol.get(index);
			if(vis!=null)
				continue;
			int cost=cur.cost;
			String path=cur.path;
			sol.put(index,cost+" "+path);
			for(int h=0;h<3;h++)
			{
				int left=cur.shiftHor(h);
				q.add(new node(left, cost+1, "H"+(h+1)+path));
			}
			for(int v=0;v<3;v++)
			{
				int down=cur.shiftVer(v);
				q.add(new node(down, cost+1, "V"+(v+1)+path));
			}
		}
	out:	while(true)
		{
			int temp,st=0;
			for(int i=8;i>=0;i--)
			{
				temp=nextInt();
				if(temp==0)
					break out;
				st+=(int)Math.pow(10,i)*temp;
			}
			String path=sol.get(st);
			out.append((path==null?"Not solvable\n":path+"\n"));
		}
		System.out.print(out);
	}
	private static int s3(int n)
	{
		int n0=n%10;
		n/=10;
		int n1=n%10;
		n/=10;
		int n2=n%10;
		int temp=n2;
		n2=n1;
		n1=n0;
		n0=temp;
		return n2*100+n1*10+n0;
	}
	public static String[] getString(int index)
	{
		String num=index+"";
		//	System.out.println(num);
			String []s=new String[3];
			for(int i=0;i<3;i++)
				s[i]=""+num.charAt(i+6)+num.charAt(i+3)+num.charAt(i);
			return s;
	}
	static class node
	{
		int index;
		int cost;
		String path;
		public node(int index, int cost, String path) {
			super();
			this.index=index;
			this.cost = cost;
			this.path = path;
		}
		public int shiftHor(int row)
		{
			int temp=index;
			int b0=temp%1000;
			temp/=1000;
			int b1=temp%1000;
			temp/=1000;
			int b2=temp%1000;
			switch(row)
			{
			case 0:b2=s3(b2);break;
			case 1:b1=s3(b1);break;
			case 2:b0=s3(b0);break;
			}
		//	index=b2*1000000+b1*1000+b0;
		//	return index;
			return b2*1000000+b1*1000+b0;
		}
		public int shiftVer(int col)
		{
			String[]s=getString(index);
			s[col]=""+s3(Integer.parseInt(s[col]));
			String res="";
			for(int i=2;i>=0;i--)
				res+=""+s[0].charAt(i)+s[1].charAt(i)+s[2].charAt(i);
		//	index=Integer.parseInt(res);
		//	return index;
			return Integer.parseInt(res);
		}
	}
}
