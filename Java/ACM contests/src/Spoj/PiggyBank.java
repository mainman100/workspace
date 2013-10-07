package Spoj;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class PiggyBank {

	static StreamTokenizer st;
	static int[]dp;
	static pair[] map;
	static final int MAX=50000*10000;
	public static int r()
	{
		try {
			st.nextToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)st.nval;
	}	
	@SuppressWarnings("deprecation")
	public static void main(String[]args)
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=r();
		StringBuffer out=new StringBuffer();
		while(t-->0)
		{
			int w=-r()+r();
			int n=r();
			map=new pair[n];
			dp=new int[w+1];
			Arrays.fill(dp,-1);
			for(int i=0;i<n;i++)
			{
				int val=r(),wt=r();
				map[i]=new pair(wt,val);
			}
			Arrays.sort(map);
			for(int i=1;i<=w;i+=1000)
				f(i);
			int res=f(w);
			if(res==MAX)
				out.append("This is impossible.\n");
			else
				out.append("The minimum amount of money in the piggy-bank is "+res+".\n");
		}
		System.out.print(out);
	}
	public static int f(int n)
	{
		if(n==0)
			return 0;
		if(dp[n]!=-1)
			return dp[n];
		int min=MAX;
		for(int i=0;i<map.length&&map[i].wt<=n;i++)
				min=Math.min(min,map[i].val+f(n-map[i].wt));
		dp[n]=min;
		return min;
		
	}
	static class pair implements Comparable<pair>
	{
		int val;
		int wt;
		public pair(int wt,int val)
		{
			this.val=val;
			this.wt=wt;
		}
		@Override
		public int compareTo(pair e) {
			return wt-e.wt;
		}	
	}
}

