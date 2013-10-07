package graphs;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Malfoy 
{


	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args) throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		int k,m,n,a,b,t;
		k=readInt();
		for(int h=0;h<k;h++)
		{
			m=readInt();
			n=readInt();
			int[][]d=new int[m][n];
			for(int i=0;i<m;i++)
			{
				for(int j=0;j<n;j++)
					d[i][j]=readInt();				
			}
			a=readInt();
			b=readInt();
			t=readInt();
			boolean visited[][]=new boolean[m][n];
			int endi=a-1;
			int endj=b-1;
			PriorityQueue<Node2> pq=new PriorityQueue<Node2>();
			pq.add(new Node2(0,0,d[0][0]));
			while(true)
			{
				Node2 cur=pq.poll();
				int i=cur.i,j=cur.j,cost=cur.cost;
				if(visited[i][j])
					continue;
				visited[i][j]=true;
				if(i==endi&&j==endj)
				{
					if(cur.cost<=t)
						out.append("YES\n"+(t-cur.cost)+"\n");
					else
						out.append("NO\n");
					break;
				}
				visited[i][j]=true;
				if(i!=0)
					pq.add(new Node2(i-1,j, cost+d[i-1][j]));
				if(i!=m-1)
					pq.add(new Node2(i+1,j, cost+d[i+1][j]));
				if(j!=0)
					pq.add(new Node2(i,j-1, cost+d[i][j-1]));
				if(j!=n-1)
					pq.add(new Node2(i,j+1, cost+d[i][j+1]));
			}	
		}
		System.out.println(out);
	}
}
class Node2 implements Comparable<Node2>
{
	int i;
	int j;
	int cost;
	public Node2(int i,int j,int c)
	{
		this.i=i;
		this.j=j;
		cost=c;
	}
	@Override
	public int compareTo(Node2 e) {
		return cost-e.cost;
	}
}
