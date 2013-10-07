package FisrtAttempts;

import java.io.*;
import static java.lang.Math.*;

@SuppressWarnings("deprecation")
public class recursive {
	static int n,m;
	static int[][] dp;
	static int[][] a;
	public static void main(String[] args) throws IOException {
		int ts = nexti();
		while(ts-->0)
		{
			n = nexti();
			m = nexti();
			a = new int[n][m];
			dp = new int[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
				{
					a[i][j] = nexti();
					dp[i][j] = -1;
				}
			int r = 0;
			for(int j = 0; j < m; j++)
				r = max(r, f(0, j));
			System.out.println(r);
		}
	}
	static StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
	static int nexti() throws IOException{
		in.nextToken();
		return (int)in.nval;
	}
	static double nextd() throws IOException{
		in.nextToken();
		return in.nval;
	}
	static String nexts() throws IOException{
		in.nextToken();
		return in.sval;
	}
	static int f(int i, int j)
	{
		if(i == n || j == m || j < 0)
			return 0;
		if(dp[i][j] != -1) return dp[i][j];
		return dp[i][j] = (max( f(i+1, j-1) , max ( f(i+1, j), f(i+1, j+1) ) ) + a[i][j]);
	}

}

