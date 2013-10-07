package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TravelSalesMan {

	static int cost[][];
	static boolean[]v;
	static int min=Integer.MAX_VALUE;
	static int wt;
	static int N;
	static int start,cur;
	public static void min()
	{
		boolean allVisited=true;
		for(int j=0;j<N;j++)
		{
			if(!v[j])
			{
				allVisited=false;
				wt+=cost[cur][j];
				v[j]=true;
				int temp=cur;
				cur=j;
				if(wt<min)
					min();
				cur=temp;
				v[j]=false;
				wt-=cost[cur][j];
			}
		}
		if(allVisited)
		{
			wt+=cost[cur][start];
			min=Math.min(min,wt);
			wt-=cost[cur][start];
		}
	}
	public static void minInit()
	{
		for(int i=0;i<N;i++)
		{
			start=i;
			cur=i;
			v[i]=true;
			min();
			v[i]=false;
		}
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		N=Integer.parseInt(tok.nextToken());
		cost=new int[N][N];
		v=new boolean[N];
		for(int i=0;i<N;i++)
		{
			tok=new StringTokenizer(st.readLine());
			for(int j=0;j<N;j++)
				cost[i][j]=Integer.parseInt(tok.nextToken());
		}
		minInit();
		System.out.println(min);
	}
}
