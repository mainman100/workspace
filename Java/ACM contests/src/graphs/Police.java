package graphs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Police {
	static StreamTokenizer st;

	private static int readInt() throws IOException 
	{
		st.nextToken();
		return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException
	{
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		int test=readInt();
		for(int w=0;w<test;w++)
		{
			int n=readInt();
			int t=readInt();
			boolean[][] vis=new boolean[n][t+1];
			int [][]time=new int[n][n];
			int [][]risk=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					time[i][j]=readInt();

			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					risk[i][j]=readInt();
			PriorityQueue<node> q=new PriorityQueue<node>();
			q.add(new node(0,0,0));
			while(true)
			{
				if(q.isEmpty())
				{
					out.append("-1\n");
					break;
				}
				node e=q.poll();
				int index=e.index;
				int timen=e.time;
				int riskn=e.risk;
				if(timen>t)
					continue;
				if(vis[index][timen])
					continue;
				vis[index][timen]=true;

				if(index==n-1)
				{
					out.append(riskn+" "+timen+"\n");
					break;
				}
				for(int j=0;j<n;j++)
					if(j!=index)
						q.add(new node(j, timen+time[index][j], riskn+risk[index][j]));
			}
		}
		System.out.print(out);
	}
}
class node implements Comparable<node> 
{
	int index;
	int time;
	int risk;
	
	public node(int index, int time, int risk) {
		this.index = index;
		this.time = time;
		this.risk=risk;
	}

	@Override
	public int compareTo(node e) 
	{
		return risk - e.risk;
	}
}
