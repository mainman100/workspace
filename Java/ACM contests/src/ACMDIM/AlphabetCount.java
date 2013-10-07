package ACMDIM;

import java.util.Arrays;

public class AlphabetCount {

	long[][][]dp=new long[51][52][27];
	char[][]grid;
	int length;
	public int count(String[] gri, int le)
	{
		grid=new char[gri.length][];
		for(int i=0;i<grid.length;i++)
			grid[i]=gri[i].toCharArray();
		length=le;
		for(int i=0;i<51;i++)
			for(int j=0;j<51;j++)
				Arrays.fill(dp[i][j], -1);
		long sum=0;
	out:	for(int i=0;i<grid.length;i++)
			for(int j=0;j<grid[i].length;j++)
				if(grid[i][j]=='A')
				{
					sum+=f(i,j,1);
					if(sum>1000000000)
					{
						sum=1000000000;
						break out;						
					}
				}
		return (int)sum;
	}
	public long f(int i,int j,int l)
	{
		if(l==length)
			return 1;
		if(dp[i][j][l]!=-1)
			return dp[i][j][l];
		int c='A'+l;
		int count=0;
		for(int dx=-1;dx<=1;dx++)
			for(int dy=-1;dy<=1;dy++)
			{
				if(dx==0&&dy==0)
					continue;
				if(i+dy>=0&&i+dy<grid.length&&j+dx>=0&&j+dx<grid[0].length)
				{
					if(grid[i+dy][j+dx]==c)
						count+=f(i+dy,j+dx,l+1);	
					if(count>1000000000)
						return 1000000000;
				}

			}
		return dp[i][j][l]=count;
	}
	public static void main(String[] args) {
		AlphabetCount a=new AlphabetCount();
		String []s={ "BDBCBACABDDCCADCBDDCBDDDBCCCCABACADDDCCCBADDDBADCA",
		  "DCBBBACBDBACCADABCCAABACDBADBCBBABACBCADADCBDABBBD" }
		;
		System.out.println(a.count(s,4));
	}
}
