package graphs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boss {


	static StringTokenizer read;
	static StringBuffer out=new StringBuffer();
	private static int readInt()throws Exception
	{
 	   return Integer.parseInt(read.nextToken());
	}
	public static void main (String[]args)throws Exception
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String line=stdin.readLine();
			if(line==null||line.equals(""))
				break;
			read=new StringTokenizer(line);
			int p=readInt();
			int r=readInt();
			int bh=readInt();
			int of=readInt();
			int yh=readInt();
			int m=readInt();
			int [][]wt=new int[p+1][p+1];
			for(int i=0;i<wt.length;i++)
				Arrays.fill(wt[i],-1);
			for(int i=0;i<r;i++)
			{
				read=new StringTokenizer(stdin.readLine());
				int p1=readInt();
				int p2=readInt();
				int d=readInt();
				wt[p1][p2]=d;
				wt[p2][p1]=d;
			}
			solve( r, bh, of, yh, m, wt);
		}
		System.out.print(out);
	}
	public static void solve(int r,int bh,int of,int yh,int m,int[][]wt)
	{
		boolean v[]=new boolean[wt.length];
		int[]d=new int[wt.length];
		ArrayList<ArrayList<Integer>> parents=new ArrayList<ArrayList<Integer>>(wt.length);
		for(int i=0;i<wt.length;i++)
		{
			parents.add(new ArrayList<Integer>());
		}
		Arrays.fill(d,(int)Math.pow(10,9));
		node start=new node(of, 0,-1);
		PriorityQueue<node> q=new PriorityQueue<node>();
		q.add(start);
		//Find all reachable nodes with shortest path of all 
		while(!q.isEmpty())
		{
			node e=q.poll();
			int p=e.p;
			int c=e.c;
			int pr=e.pr;
			if(v[p])
			{
				if(c==d[p])
					parents.get(p).add(pr);
				continue;
			}
			v[p]=true;
			d[p]=c;
			parents.get(p).add(pr);
			for(int i=0;i<wt.length;i++)
			{
				if(wt[p][i]==-1)
					continue;
				q.add(new node(i,c+wt[p][i],p));
			}
		}
	//	print(parents,bh,of);
		Arrays.fill(v,false);
		Queue<Integer> qi=new LinkedList<Integer>();
		qi.add(bh);
		while(!qi.isEmpty())
		{
			int p=qi.poll();
			v[p]=true;
			ArrayList<Integer> prs=parents.get(p);
			for(int i=0;i<prs.size();i++)
			{
				if(prs.get(i)==-1||v[prs.get(i)]==true)
					continue;
				qi.add(prs.get(i));
			}
		}
		q.clear();
		v[of]=true;
		if(v[yh]||v[m])
		{
			out.append("MISSION IMPOSSIBLE.\n");
			return;
		}
		start=new node(yh,0);
		q.add(start);
		while(true)
		{
			if(q.isEmpty())
			{
				out.append("MISSION IMPOSSIBLE.\n");
				return;
			}
			node e=q.poll();
			int p=e.p;
			v[p]=true;
			int c=e.c;
			if(p==m)
			{
				out.append(c+"\n");
				return;
			}
			for(int i=0;i<wt.length;i++)
			{
				if(wt[p][i]==-1)
					continue;
				if(v[i]==true)
					continue;
				q.add(new node(i,c+wt[p][i]));
			}
		}
	}
/*	public static void print(ArrayList<ArrayList<Integer>> paths,int start,int end)
	{
		if(start==end)
		{
			System.out.print(end);
			return;
		}
		ArrayList<Integer> path=paths.get(start);
		for(int i=0;i<path.size();i++)
		{
			System.out.print(start+" ");
			print(paths,path.get(i),end);
			System.out.println();
		}
	}*/
	static class node implements Comparable<node>
	{
		int p,c,pr;

		public node(int p, int c,int pr) {
			super();
			this.p = p;
			this.c = c;
			this.pr=pr;
		}
		public node(int p, int c) {
			super();
			this.p = p;
			this.c = c;
		}
		@Override
		public int compareTo(node e) 
		{
			return c-e.c;
		}
	}
}
