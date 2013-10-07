package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEQ {

	static int m=1000000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int c=Integer.parseInt(st.readLine().trim());
		while(c-->0)
		{
			int k=Integer.parseInt(st.readLine().trim());
			long[][]a=new long[k][k];
			long[][]base=new long[k][1];
			StringTokenizer tok=new StringTokenizer(st.readLine().trim());
			for(int i=k-1;i>=0;i--)
				base[i][0]=Long.parseLong(tok.nextToken());
			tok=new StringTokenizer(st.readLine().trim());
			for(int i=0;i<k;i++)
				a[0][i]=Long.parseLong(tok.nextToken());
			for(int i=1;i<k;i++)
				a[i][i-1]=1;
			int n=Integer.parseInt(st.readLine().trim());
			if(n<=k)
			{
				System.out.println(base[k-n][0]);
				continue;
			}
			int pow=n-k;
			long[][]res=new long[k][k];
			for(int i=0;i<k;i++)
				res[i][i]=1;
			while(pow>0)
			{
				if((pow&1)!=0)
					res=mult(res, a);
				a=mult(a,a);
				pow=pow>>1;
			}
			res=mult(res,base);
			System.out.println((res[0][0]+m)%m);
		}
	}
	public static long[][] mult(long[][] a, long[][] b) {
		long[][] c = new long[a.length][b[0].length];
		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[0].length; j++)
				for (int k = 0; k < a[0].length; k++)
					c[i][j] =(c[i][j]+ (a[i][k] * b[k][j])%m+m)%m;
		return c;
	}
}
