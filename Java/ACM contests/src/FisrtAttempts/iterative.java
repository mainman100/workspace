package FisrtAttempts;

import java.io.*;
import static java.lang.Math.*;

@SuppressWarnings("deprecation")
public class iterative {
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
			for (int i = n-1; i >= 0; i--)
				for (int j = 0; j < m; j++)
					if(i == n-1)
						dp[i][j] = a[i][j];
					else
						dp[i][j] = max(dp[i+1][j], max(
								j > 0 ? dp[i+1][j-1] : 0, j < m-1 ? dp[i+1][j+1] : 0))
								+ a[i][j];
			int r = 0;
			for(int k: dp[0])
				r = max(r, k);
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
}

