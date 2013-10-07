package Uva;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class RoyalGreediness {

	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int casee=1;
		while(true)
		{
			int farmers=r();
			if(farmers==-1)
				break;
			pair[]a=new pair[farmers];
			node[]b=new node[2*farmers];
			for(int i=0;i<farmers;i++)
			{
				int start=r();
				int end=r();
				a[i]=new pair(start, end);	
				b[2*i]=new node(start,true);
				b[2*i+1]=new node(end, false);
			}
		//	System.out.println("Case "+casee+": "+solve(a));
			System.out.println("Case "+casee+": "+solve(b));
			casee++;

		}
	}
	public static int solve(pair[]a)
	{
		if(a.length==0)
			return 0;
		Arrays.sort(a);
		int[]res=new int[a.length];
		for(int i=0;i<a.length;i++)
			for(int j=i+1;j<a.length;j++)
			{
				if(a[i].end>=a[j].start)
					res[i]++;
			}
		Arrays.sort(res);
		return res[res.length-1]+1;
	}
	public static int solve(node[]a)
	{
		Arrays.sort(a);
		int res=0;
		int max=0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i].start)
				res++;
			else
				res--;
			max=Math.max(max,res);
		}
		return max;
	}
	static class pair implements Comparable<pair>
	{
		int start,end;
		public pair(int s,int e)
		{
			start=s;
			end=e;
		}
		@Override
		public int compareTo(pair arg0) {
			// TODO Auto-generated method stub
			return end-arg0.end;
		}
	}
	static class node implements Comparable<node>
	{
		int num;
		boolean start;
		public node(int num, boolean start) {
			super();
			this.num = num;
			this.start = start;
		}
		@Override
		public int compareTo(node arg) {
			// TODO Auto-generated method stub
			if(num<arg.num)
				return -1;
			if(num>arg.num)
				return 1;
			if(start&&!arg.start)
				return -1;
			if(!start&&arg.start)
				return 1;
			return 0;
		}
	}
}
