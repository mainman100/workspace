package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingSticks {

	static int[]cuts;
	static int dp[][];
	public static void main(String[] args) throws IOException {
		
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			int l=Integer.parseInt(st.readLine());
			if(l==0)
				break;
			int n=Integer.parseInt(st.readLine());
			cuts=new int[n];
			StringTokenizer tok=new StringTokenizer(st.readLine());
			for(int i=0;i<n;i++)
				cuts[i]=Integer.parseInt(tok.nextToken());
			dp=new int[l+1][l+1];
			for(int i=0;i<=l;i++)
				Arrays.fill(dp[i], -1);
			System.out.println("The minimum cutting is "+f(0,l)+".");
		}
	}
	public static int f(int i,int j)
	{
		int cost=j-i;
		int begin=-1;
		for(int c=0;c<cuts.length;c++)
		{
			if(cuts[c]>i&&cuts[c]<j)
			{
				begin=c;
				break;
			}
		}
		if(begin==-1)
			return 0;
		int end=-1;
		for(int c=cuts.length-1;c>=0;c--)
		{
			if(cuts[c]<j&&cuts[c]>i)
			{
				end=c;
				break;
			}
		}
		int min=1<<30;
		for(int c=begin;c<=end;c++)
			min=Math.min(min, cost+f(i,cuts[c])+f(cuts[c],j));
		return min;
	}
}
