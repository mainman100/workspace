package Spoj;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class SCUBADIV {

	static cyl arr[];
	static int[][]dp;
	static StreamTokenizer st;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=r();
		while(t-->0)
		{
			int oxg=r();
			int net=r();
			int n=r();
			arr=new cyl[n];
			for(int i=0;i<n;i++)
			{
				int ox=r(),ne=r(),wt=r();
				arr[i]=new cyl(ox,ne, wt);
			}
			dp=new int[oxg+1][net+1];
			for(int i=0;i<dp.length;i++)
				Arrays.fill(dp[i], -1);
			boolean[]v=new boolean[n];
			System.out.println(f(oxg,net,v));
		}
	}
	public static int f(int oxg,int net,boolean[]v)
	{
		if(dp[oxg][net]!=-1)
			return dp[oxg][net];
		if(oxg==0&&net==0)
			return 0;
		int min=Integer.MAX_VALUE;
		for(int i=0;i<arr.length;i++)
		{
			if(v[i])
				continue;
			boolean[] vs=Arrays.copyOf(v,v.length);
			vs[i]=true;
			min=Math.min(min,arr[i].wt+f(Math.max(oxg-arr[i].oxg, 0),Math.max(0,net-arr[i].net),vs));
		}
		return dp[oxg][net]=min;
	}
	static class cyl
	{
		int oxg;
		int net;
		int wt;
		public cyl(int oxg, int net, int wt) {
			super();
			this.oxg = oxg;
			this.net = net;
			this.wt = wt;
		}
	}
}
