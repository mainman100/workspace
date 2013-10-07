package graphs;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MELE3 {



	static StreamTokenizer st;
	static boolean[][]map;
	static int k,n;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		k=readInt();
		n=readInt();
		map=new boolean[k+1][k+1];
		for(int i=0;i<map.length;i++)
			Arrays.fill(map[i],false);
		for(int i=0;i<n;i++)
		{
			int e1=readInt();
			int e2=readInt();
			map[e1][e2]=true;
			map[e2][e1]=true;
		}
		System.out.println(dikstra(1, k));
	}
	public static int dikstra(int s,int end)
	{
		PriorityQueue<node> pq=new PriorityQueue<node>();
		boolean []v=new boolean[k+1];
		pq.add(new node(s,0));
		while(!pq.isEmpty())
		{
			node e=pq.poll();
			int index=e.index;
			int cost=e.cost;
			if(v[index])
				continue;
			if(index==end)
				return cost*5;
			v[index]=true;
			for(int i=1;i<map.length;i++)
			{
				if(map[index][i]&&v[i]==false)
				{
					int wt=Math.abs(index-i);
					int temp=0;
					if(index<i)
						temp=((2*wt)-cost%(2*wt))%(2*wt);
					else
					{
						int cost1=cost+wt;
						temp=((2*wt)-cost1%(2*wt))%(2*wt);
					}
					pq.add(new node(i,cost+temp+wt));
				}
			}
		}
		return 0;
	}
	static class node implements Comparable<node>
	{
		int index,cost;

		public node(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(node e) 
		{
			return cost-e.cost;
		}
	}
}
