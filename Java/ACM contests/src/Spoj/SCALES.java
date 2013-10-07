package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SCALES {

	static int n,l,d;
	static String w;
	static int[][]dp;
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			StringTokenizer tok=new StringTokenizer(st.readLine());
			n=Integer.parseInt(tok.nextToken());
			l=Integer.parseInt(tok.nextToken());
			d=Integer.parseInt(tok.nextToken());
			w=st.readLine().trim();
			StringBuffer temp=new StringBuffer(w);
			w=temp.reverse().toString();
			dp=new int[2][n];
			for(int i=0;i<2;i++)
				Arrays.fill(dp[i], -1);
			for(int i=n-1;i>=0;i-=1000)
			{
				f(i,0);
				f(i,1);
			}
			System.out.println(f(0,0));
		}
	}
	public static int f(int i,int c)
	{
		if(i==n)
			return 1-c;
		if(dp[c][i]!=-1)
			return dp[c][i];
		int wBit=i<l?w.charAt(i)-'0':0;
		if((wBit^c)==1)
			return dp[c][i]=(f(i+1,0)+f(i+1,1))%d;
		return dp[c][i]=f(i+1,c);
	}
}
