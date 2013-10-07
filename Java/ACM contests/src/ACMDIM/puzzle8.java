package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class puzzle8 {

	static HashSet<Integer> v=new HashSet<Integer>();
	static int[][]ar=new int[3][3];
	static int END=123456780;
	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		String line=st.readLine();
		StringTokenizer tok=new StringTokenizer(line);
		String num="";
		for(int i=0;i<9;i++)
			num+=tok.nextToken();
		int res=solve(Integer.parseInt(num));
		if(res==-1)
			System.out.println("No");
		else
			System.out.println("Yes\n"+res);
	}
	public static int solve(int start)
	{
		Queue<pair> q=new LinkedList<pair>();
		q.add(new pair(start,0));
		int[]dx={-1,0,0,1};
		int[]dy={0,-1,1,0};
		while(!q.isEmpty())
		{
			pair e=q.poll();
			int i=e.i;
			int c=e.c;
			if(v.contains(i))
				continue;
			v.add(i);
			if(i==END)
				return c;
			for(int j=0;j<4;j++)
			{
				int newNum=move(i,dx[j],dy[j]);
				if(newNum!=-1)
					q.add(new pair(newNum,c+1));
			}
		}
		return -1;
	}
	public static int move(int num,int dx,int dy)
	{
		int xpos=0,ypos=0;
		for(int i=2;i>=0;i--)
			for(int j=2;j>=0;j--)
			{
				ar[i][j]=num%10;
				num/=10;
				if(ar[i][j]==0)
				{
					xpos=j;
					ypos=i;
				}
			}
		if(ypos+dy<0||ypos+dy>=3||xpos+dx<0||xpos+dx>=3)
			return -1;
		ar[ypos][xpos]=ar[ypos+dy][xpos+dx];
		ar[ypos+dy][xpos+dx]=0;
		int res=0;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				res=res*10+ar[i][j];
		return res;
		
	}
	static class pair
	{
		int i, c;

		public pair(int i, int c) {
			super();
			this.i = i;
			this.c = c;
		}
		
	}
}
