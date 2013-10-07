package BUECamp;
/*import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;


public class LeapFrog {

	static StreamTokenizer st;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			int n=r();
			if(n==0)
				break;
			int[]a=new int[n];
			for(int i=0;i<n;i++)
				a[i]=r();
			System.out.println(solve(a));
		}
	}
	public static int solve(int[]a)
	{
		HashSet<Integer> set=new HashSet<Integer>();
		for(int i=0;i<a.length;i++)
			set.add(a[i]);
		int n=a.length;
		int[][]dp=new int[n][n];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i],-1);
		dp[a[0]][a[1]]=0;
		for(int i=a[0];i<n;i++)
			for(int j=a[1];j<n;j++)
			{
				
			}
	}
}
*/