package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Graph {

	static int[][]dp;
	static boolean[][][]g;
	static int n;
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		n=Integer.parseInt(tok.nextToken());
		int m=Integer.parseInt(tok.nextToken());
		dp=new int[n][4];
		g=new boolean[n][n][4];
		for(int i=0;i<m;i++)
		{
			tok=new StringTokenizer(st.readLine());
			int x=Integer.parseInt(tok.nextToken())-1;
			int y=Integer.parseInt(tok.nextToken())-1;
			int c=Integer.parseInt(tok.nextToken());
			g[x][y][c]=true;
		}
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i],-1);
		int res=f(0,0);
		if(res==300)
			System.out.println(-1);
		else
			System.out.println(res);
	}
	public static int f(int i,int prevColor)
	{
		if(dp[i][prevColor]==-2)
			return 300;
		if(dp[i][prevColor]!=-1)
			return dp[i][prevColor];
		if(i==n-1)
			return 0;
		dp[i][prevColor] = -2;
		int min=300;
		for(int j=0;j<n;j++)
			for(int c=1;c<=3;c++)
				if(g[i][j][c]&&c!=prevColor)
					min=Math.min(min,1+f(j,c));
		return dp[i][prevColor]=min;
	}
}
