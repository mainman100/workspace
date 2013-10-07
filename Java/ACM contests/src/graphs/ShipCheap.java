package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ShipCheap
{
	static int[]pr=new int[2626];
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		boolean first=true;
		while(true)
		{
			String num=stdin.readLine();
			if(num==null||num.length()==0)
				break;
			if(!first)
				out.append("\n");
			first=false;
			int n=Integer.parseInt(new StringTokenizer(num).nextToken());
			boolean[]vis=new boolean[2626];
			boolean[][]adj=new boolean[2626][2626];
			Arrays.fill(pr,-1);
			for(int i=0;i<n;i++)
			{
				StringTokenizer st=new StringTokenizer(stdin.readLine());
				String s1=st.nextToken();
				String s2=st.nextToken();
				int x=100*(s1.charAt(0)-'A')+(s1.charAt(1)-'A');
				int y=100*(s2.charAt(0)-'A')+(s2.charAt(1)-'A');
				adj[x][y]=true;
				adj[y][x]=true;
			}
			StringTokenizer st=new StringTokenizer(stdin.readLine());
			String s1=st.nextToken();
			String s2=st.nextToken();
			int start=100*(s1.charAt(0)-'A')+(s1.charAt(1)-'A');
			int end=100*(s2.charAt(0)-'A')+(s2.charAt(1)-'A');
			Queue<node> q=new LinkedList<node>();
			q.add(new node(start,-1));
			while(true)
			{
				if(q.isEmpty())
				{
					out.append("No route\n");
					break;
				}
				node cur=q.poll();
				int index=cur.index;
				if(vis[index])
					continue;
				vis[index]=true;
				pr[index]=cur.parent;
				if(index==end)
				{
					out.append(getRoute(start,end));
					break;
				}
				for(int i=0;i<2626;i++)
					if(adj[index][i])
						q.add(new node(i,index));
			}
			num=stdin.readLine();
		}
		System.out.print(out);
	}
	private static String getRoute(int start,int end)
	{
		int temp=end;
		String res="";
		while(temp!=start)
		{
			res=getString(pr[temp])+" "+getString(temp)+"\n"+res;
			temp=pr[temp];
		}
		return res;
	}
	private static String getString(int n)
	{
		char c1=(char)(n%100+'A');
		char c2=(char)(n/100+'A');
		return ""+c2+c1;
	}
	static class node
	{
		int index;
		int parent;
		public node(int i,int p)
		{
			index=i;
			parent=p;
		}
	}
}