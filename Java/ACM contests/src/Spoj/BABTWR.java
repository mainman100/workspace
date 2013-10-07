package Spoj;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class BABTWR {

	static block[]blocks;
	static int[]dp;
	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args) throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			int n=r();
			if(n==0)
				break;
			blocks=new block[6*n+1];
			dp=new int[6*n+1];
			for(int i=0;i<n;i++)
			{
				int x=r(),y=r(),z=r();
				blocks[6*i]=new block(x, y, z);
				blocks[6*i+1]=new block(x, z, y);
				blocks[6*i+2]=new block(y, x, z);
				blocks[6*i+3]=new block(y, z, x);
				blocks[6*i+4]=new block(z, x, y);
				blocks[6*i+5]=new block(z, y, x);
			}
			blocks[6*n]=new block(Integer.MAX_VALUE,Integer.MAX_VALUE,0);
			Arrays.fill(dp,-1);
			System.out.println(f(6*n));
		}
	}
	public static int f(int ground)
	{
		if(dp[ground]!=-1)
			return dp[ground];
		block base=blocks[ground];
		int xg=base.x;
		int yg=base.y;
		int max=0;
		for(int i=0;i<blocks.length;i++)
		{
			if(blocks[i].x<xg&&blocks[i].y<yg)
				max=Math.max(max,blocks[i].z+f(i));		
		}
		return dp[ground]=max;
	}
	static class block
	{
		int x,y,z;
		public block(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
