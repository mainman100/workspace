package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LISA {

	static int[][]dp;
	static int[]num;
	static boolean[]adds;
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		int t=Integer.parseInt(stdin.readLine());
		while(t-->0)
		{
			String arth=stdin.readLine();
			num=new int[arth.length()/2+1];
			adds=new boolean[num.length-1];
			dp=new int[num.length][num.length];
			for(int i=0;i<adds.length;i++)
			{
				num[i]=arth.charAt(2*i)-'0';
				adds[i]=arth.charAt(2*i+1)=='+'?true:false;
			}
			num[num.length-1]=arth.charAt(arth.length()-1)-'0';
			for(int i=0;i<num.length;i++)
				Arrays.fill(dp[i],-1);
			out.append(fMax(0,num.length-1)+"\n");
			for(int i=0;i<num.length;i++)
				Arrays.fill(dp[i],-1);
			out.append(fMin(0,num.length-1)+"\n");
		}
		System.out.print(out);
	}
	public static int fMax(int i,int j)
	{
		if(dp[i][j]!=-1)
			return dp[i][j];
		if(i==j)
			return num[i];
		int max=0;
		for(int k=i;k<j;k++)
		{
			int temp=0;
			boolean add=adds[k];
			if(add)
				temp=fMax(i,k)+fMax(k+1,j);
			else
				temp=fMax(i,k)*fMax(k+1,j);
			max=Math.max(max,temp);
		}
		return dp[i][j]=max;
	}
	public static int fMin(int i,int j)
	{
		if(dp[i][j]!=-1)
			return dp[i][j];
		if(i==j)
			return num[i];
		int min=Integer.MAX_VALUE;
		for(int k=i;k<j;k++)
		{
			int temp=0;
			boolean add=adds[k];
			if(add)
				temp=fMin(i,k)+fMin(k+1,j);
			else
				temp=fMin(i,k)*fMin(k+1,j);
			min=Math.min(min,temp);
		}
		return dp[i][j]=min;
	}
}
