package ACMDIM;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;


public class TimeToTakeStones {

	static int[]grand;
	static int n,m;
	static int[]moves;
	static int[]dp;
	static StreamTokenizer st;
	
	static boolean []lose;
	public static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void calcGrand()
	{
		grand=new int[n+1];
		grand[0]=0;
		HashSet<Integer> prevGrand=new HashSet<Integer>();
		for(int i=1;i<=n;i++)
		{
			for(int j=0;j<moves.length;j++)
			{
				if(i-moves[j]>=0)
					prevGrand.add(grand[i-moves[j]]);
			}
			int res=0;
			while(prevGrand.contains(res))
				res++;
			grand[i]=res;
			prevGrand.clear();
		}
	}
	public static int canWin(int i)
	{
		if(dp[i]!=-1)
			return dp[i];
		for(int j:moves)
		{
			if(i-j>=0)
			{
				if(canWin(i-j)==2)
					return dp[i]=1;
			}
		}
		return dp[i]=2;
	}
	public static void fillDp()
	{
		for(int i=1;i<n;i++)
			if(lose[i])
				for(int j:moves)
					if(i+j<=n)
						lose[i+j]=false;
				
	}
	public static void main(String[] args) throws IOException {
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		n=r();
		m=r();
		moves=new int[m];
		for(int i=0;i<m;i++)
			moves[i]=r();
		dp=new int[n+1];
		lose=new boolean[n+1];
		Arrays.fill(lose,true);
		Arrays.fill(dp,-1);
		dp[0]=1;
		System.out.println(canWin(n));
	}
}
