package Uva;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class BeeLove {

	static boolean[][]adj=new boolean[19][19];
	static int[]res=new int[19];
	static 
	{
		for(int i=0;i<19;i++)
			res[i]=i;
	}
	static {
		for(int i=7;i<=17;i++)
		{
			adj[i][i+1]=true;
			adj[i+1][i]=true;
		}
		adj[18][7]=true;
		adj[7][18]=true;
		
		for(int i=1;i<=5;i++)
		{
			adj[i][i+1]=true;
			adj[i+1][i]=true;
		}
		adj[5][6]=true;
		adj[6][5]=true;
		
		for(int i=1;i<=6;i++)
		{
			adj[0][i]=true;
			adj[i][0]=true;
		}
		int i=1;
		int j=7;
		while(i<=6)
		{
			adj[i][j]=true;
			adj[i][j+1]=true;
			adj[j][i]=true;
			adj[j+1][i]=true;
			if(j+2==19)
			{
				adj[6][7]=true;
				adj[7][6]=true;
			}
			else
			{
				adj[i][j+2]=true;
				adj[j+2][i]=true;	
			}
			i++;
			j+=2;
		}
	}
	public static void main(String[]args)
	{
		
	}
	public static int dik(int[]start,int empt)
	{
		HashSet<node> set=new HashSet<node>();
		Queue<node>q=new LinkedList<node>();
		while(true)
		{
			node e=q.poll();
			int[]a=e.a;
			int c=e.c;
			if(c>20)
				return 21;
			if(Arrays.equals(a, res))
				return c;
			int empty=e.empty;
			if(set.contains(e))
				continue;
			for(int i=0;i<adj.length;i++)
			{
				if(adj[empty][i])
				{
					int[]b=Arrays.copyOf(a,a.length);
					swap(b,empty,i);
					node t=new node(b, i,c+1);
					if(set.contains(t))
						continue;
					q.add(t);
				}
			}
		}
	}
	static class node
	{
		int []a;
		int empty;
		int c;
		public node(int []a, int empty,int c) {
			super();
			this.a = a;
			this.empty = empty;
			this.c=c;
		}
		public boolean equals(Object e)
		{
			int[]b=(int[])e;
			return Arrays.equals(a,b);
		}
		public int hashCode()
		{
			return Arrays.hashCode(a);
		}
	}
	public static void swap(int[]a,int i,int j)
	{
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	
}
