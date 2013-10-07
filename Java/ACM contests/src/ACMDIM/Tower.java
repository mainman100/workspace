package ACMDIM;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class Tower {

	static StreamTokenizer st;

	private static long r() throws Exception {
		st.nextToken();
		return (long) st.nval;
	}

	public static long[][] mult(long[][] a, long[][] b, long m) {
		long[][] c = new long[a.length][b[0].length];
		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[0].length; j++)
				for (int k = 0; k < a[0].length; k++)
					c[i][j] = (c[i][j] + m + (a[i][k] * b[k][j] + m) % m) % m;
		return c;
	}

	public static long[][] pow(long[][] a, long pow, long m) {
		if (pow == 1)
			return a;
		if (pow % 2 == 0) {
			long[][] b = mult(a, a, m);
			return pow(b, pow / 2, m);
		} else {
			long[][] b = mult(a, a, m);
			return mult(pow(b, pow / 2, m), a, m);
		}
	}
	public static long[][]power(long[][]a,long pow,long m)
	{
		int n=a.length;
		long[][]res=new long[n][n];
		for(int i=0;i<n;i++)
			res[i][i]=1;
		while(pow>0)
		{
			if((pow&1)!=0)
				res=mult(res, a,m);
			a=mult(a,a,m);
			pow=pow>>1;
		}
		return res;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		StringBuffer out = new StringBuffer();
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		long t = r();
		while (t-- > 0) {
			long b = 2 * r();
			long n = r();
			long m = r();
			if (n == 1)
				out.append(1);
			else if (n == 2)
				out.append(b * b / 4 + 1);
			else {
				long[][] matrix = new long[][] { { b * b, 1, -2 * b, 0 },
						{ 1, 0, 0, 0 }, { b, 0, -1, 0 },
						{ b * b, 1, -2 * b, 1 } };
				long[][] base = { { b * b / 4 }, { 1 }, { b / 2 },
						{ 1 + b * b / 4 } };
				long[][]pow=power(matrix,n-2,m);
				long [][]res=mult(pow,base,m);
				
				out.append(res[3][0] + "\n");
			}
		}
		System.out.print(out);
	}
}
