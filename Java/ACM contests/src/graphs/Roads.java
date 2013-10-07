package graphs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Roads {
	static StreamTokenizer st;

	private static int readInt() throws IOException 
	{
		st.nextToken();
		return (int) st.nval;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) throws IOException
	{
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		int test=readInt();
		for(int w=0;w<test;w++)
		{
			int k=readInt();
			int n=readInt();
			int r=readInt();
			boolean[][] vis=new boolean[n][k+1];
			ArrayList<road> [][]roads=new ArrayList[n][n];
			for(int i=0;i<roads.length;i++)
				for(int j=0;j<roads[0].length;j++)
					roads[i][j]=new ArrayList<road>();
			for(int i=0;i<r;i++)
			{
				int s1=readInt()-1;
				int s2=readInt()-1;
				roads[s1][s2].add(new road(readInt(),readInt())); 
			}
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
				int Length=e.length;
				int Toll=e.toll;
				if(Toll>k)
					continue;
			//	if(vis[index][Toll])
			//		continue;
				for(int i=0;i<=Toll;i++)
					if(vis[index][i])
						continue;
				vis[index][Toll]=true;

				if(index==n-1)
				{
					out.append(Length+"\n");
					break;
				}
				for(int j=0;j<n;j++)
				{
					ArrayList<road> neg=roads[index][j];
					for(int u=0;u<neg.size();u++)
					{
						road rd=neg.get(u);
						q.add(new node(j, Length+rd.length, Toll+rd.toll));
					}
				}
			}
		}
		System.out.print(out);
	}
	static class node implements Comparable<node> 
	{
		int index;
		int length;
		int toll;
		
		public node(int index, int length, int toll) {
			super();
			this.index = index;
			this.length = length;
			this.toll = toll;
		}

		@Override
		public int compareTo(node e) 
		{
			return length - e.length;
		}
	}
	static class road
	{
		int length;
		int toll;
		public road(int length, int toll) {
			super();
			this.length = length;
			this.toll = toll;
		}
		
	}
}

