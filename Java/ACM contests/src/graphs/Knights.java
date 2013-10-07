package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Knights 
{
	public static void main(String[]args) throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		String s=null;
		boolean [][]visited=new boolean[8][8];
		int []cases={-1,2,1,2,-1,-2,1,-2,-2,1,2,1,-2,-1,2,-1};
		while((s=stdin.readLine())!=null)
		{
			if(s.length()==0)
				break;
			for(int i=0;i<8;i++)
				Arrays.fill(visited[i],false);
			StringTokenizer st=new StringTokenizer(s);
			String first=st.nextToken();
			String second=st.nextToken();
			int si=first.charAt(0)-'a';
			int sj=first.charAt(1)-'1';
			int ei=second.charAt(0)-'a';
			int ej=second.charAt(1)-'1';
			Queue<Node> q=new LinkedList<Node>();
			q.add(new Node(si,sj,0));
			while(true)
			{
				Node cur=q.poll();
				int i=cur.i;
				int j=cur.j;
				int cost=cur.cost;
				if(visited[i][j])
					continue;
				if(i==ei&&j==ej)
				{
					out.append("To get from "+first+" to "+second+" takes "+cost+" knight moves.\n");
					break;
				}
				visited[i][j]=true;
				for(int h=0;h<cases.length;h=h+2)
					if(i+cases[h]>=0&&i+cases[h]<=7&&j+cases[h+1]>=0&&j+cases[h+1]<=7)
						q.add(new Node(i+cases[h],j+cases[h+1],cost+1));
			}
		}
		System.out.print(out);
	}
	static class Node
	{
		int i;
		int j;
		int cost;
		public Node(int i, int j, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
	}
}

