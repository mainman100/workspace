package Spoj;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class PERMUTE1 {

	static int[][]dp;
	static StreamTokenizer st;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[]args) throws  IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=r();
		while(t-->0)
		{
			int n=r();
			int k=r();
			dp=new int[n+1][k+1];
			for(int i=1;i<dp.length;i++)
				Arrays.fill(dp[i],-1);
			for(int i=1;i<dp.length;i++)
				dp[i][0]=1;
			System.out.println(f(n,k));
		}
	}
	public static int f(int n,int k)
	{
		if(dp[n][k]!=-1)
			return dp[n][k];
		int sum=0;
		for(int i=n-1;k-(n-1-i)>=0&&i>=0;i--)
			sum+=f(n-1,k-(n-1-i));
		return dp[n][k]=sum;
	}
}

