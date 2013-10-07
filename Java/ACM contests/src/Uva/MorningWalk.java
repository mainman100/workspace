package Uva;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class MorningWalk {

	static StreamTokenizer st;
	static boolean[]v;
	static int[][]adj;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   if(st.ttype==StreamTokenizer.TT_EOF)
 		   return -1;
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			int n=r();
			v=new boolean[n];
			if(n==-1)
				break;
			int r=r();
			adj=new int[n][n];
			for(int k=0;k<r;k++)
			{
				int i=r();
				int j=r();
				adj[i][j]++;
			}
			boolean connected=connected(0);
			if(!connected)
			{
				System.out.println("Not Possible");
				continue;
			}
			int odd=0;
			for(int i=0;i<n;i++)
			{
				int tempOdd=0;
				for(int j=0;j<n;j++)
				{
					tempOdd+=adj[i][j];
				}
				odd+=tempOdd%2;
			}
			if(odd==2||odd==0)
				System.out.println("Possible");
			else
				System.out.println("Not Possible");
		}
				
	}
	public static boolean connected(int start)
	{
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(start);
		while(!q.isEmpty())
		{
			int i=q.poll();
			if(v[i])
				continue;
			v[i]=true;
			for(int j=0;j<adj.length;j++)
				if(adj[i][j]>0||!v[j])
					q.add(j);
		}
		for(int i=0;i<adj.length;i++)
			if(!v[i])
				return false;
		return true;
	}
	
}
