import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class LeastRound {

	static int[][][] dp;
	static StreamTokenizer st;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		st=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n =r();
		dp = new int[n][n][2];
		int zeroRow=-1;
		boolean zero=false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int m = r();
				if(m==0)
				{
					zero=true;
					zeroRow=i;
				}
				dp[i][j][0] = pow2(m);
				dp[i][j][1] = pow5(m);
			}
		}

		for(int i=1;i<n;i++)
			for(int k=0;k<2;k++)
			{
				dp[0][i][k]+=dp[0][i-1][k];
				dp[i][0][k]+=dp[i-1][0][k];
				
			}
		for(int i=1;i<n;i++)
			for(int j=1;j<n;j++)
				for(int k=0;k<2;k++)
					dp[i][j][k]+=Math.min(dp[i-1][j][k], dp[i][j-1][k]);
		int res=Math.min(dp[n-1][n-1][0], dp[n-1][n-1][1]);
		char[]path=new char[2*n-2];
		int pos=2*n-3;
		int k=dp[n-1][n-1][0]<dp[n-1][n-1][1]?0:1;
		if(!zero||zero&&(res==0))
		{
			System.out.println(res);
			int i=n-1,j=n-1;
			while(i!=0&&j!=0)
			{
				if(dp[i][j-1][k]<dp[i-1][j][k])
				{
					path[pos--]='R';
					j--;
				}
				else
				{
					path[pos--]='D';
					i--;
				}
			}
			if(i==0)
			{
				for(;j>0;j--)
					path[pos--]='R';
			}
			else if(j==0)
			{
				for(;i>0;i--)
					path[pos--]='D';
			}
		}
		else
		{
			System.out.println(1);
			pos=0;
			int i=0,j=0;
			for(;i<zeroRow;i++)
				path[pos++]='D';
			for(;j<n-1;j++)
				path[pos++]='R';
			for(;i<n-1;i++)
				path[pos++]='D';
		}
		System.out.println(path);
	}
	private static int pow2(int n) {
		if (n == 0)
			return 1;
		int res = 0;
		while ((n & 1) == 0) {
			res++;
			n >>= 1;
		}
		return res;

	}

	private static int pow5(int n) {
		if (n == 0)
			return 1;
		int res = 0;
		while (n % 5 == 0) {
			res++;
			n /= 5;
		}
		return res;
	}
}
