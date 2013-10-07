package Spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Mixtures {

	static int[][] dp;
	static int[][]rem;
	static int[]mix;
	static int n;
	public static void main(String[]args) throws Exception
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String line=stdin.readLine();
			if(line==null||line.length()==0)
				break;
			n=Integer.parseInt(line);
			mix=new int[n];
			String []data=stdin.readLine().split(" ");
			for(int i=0;i<n;i++)
				mix[i]=Integer.parseInt(data[i]);
			dp=new int[n][n];
			for(int i=0;i<n;i++)
				Arrays.fill(dp[i],-1);
			rem=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=i;j<n;j++)
					for(int k=i;k<=j;k++)
						rem[i][j]+=mix[k];
			for(int i=0;i<n;i++)
				for(int j=i;j<n;j++)
					rem[i][j]%=100;
		//	dp();
		//	System.out.println(dp[0][n-1]);
			System.out.println(recursive(0,n-1));
		}
	}
	public static int recursive(int i,int j)
	{
		if(i==j)
			return 0;
		if(i-j==1)
			return mix[i]*mix[j];
		if(dp[i][j]!=-1)
			return dp[i][j];
		int min=Integer.MAX_VALUE;
		for(int k=i;k<j;k++)
		{
			int smoke=rem[i][k]*rem[k+1][j];
			min=Math.min(min,smoke+recursive(i,k)+recursive(k+1,j));
		}
		return dp[i][j]=min;
	}
	public static void iterative()
	{
		for(int l=2;l<=n;l++)
		{
			for(int i=0;i<=n-l;i++)
			{
				int j=i+l-1;
				int min=Integer.MAX_VALUE;
				for(int k=i;k<j;k++)
				{
					int smoke=rem[i][k]*rem[k+1][j];
					min=Math.min(min,smoke+dp[i][k]+dp[k+1][j]);
				}
				dp[i][j]=min;
			}
		}
	}
}
