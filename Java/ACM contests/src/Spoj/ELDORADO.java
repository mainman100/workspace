package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ELDORADO {

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			StringTokenizer tok=new StringTokenizer(st.readLine());
			int n=Integer.parseInt(tok.nextToken());
			int k=Integer.parseInt(tok.nextToken());
			if(n==0&&k==0)
				break;
			int[]a=new int[n];
			tok=new StringTokenizer(st.readLine());
			for(int i=0;i<n;i++)
				a[i]=Integer.parseInt(tok.nextToken());
			long[][]dp=new long[n][k+1];
			for(int i=0;i<n;i++)
				dp[i][1]=1;
			for(int l=2;l<=k;l++)
				for(int i=0;i<n;i++)
					for(int j=0;j<i;j++)
						if(a[j]<a[i])
							dp[i][l]+=dp[j][l-1];
			for(int i=0;i<n;i++)
				System.out.println(dp[i][k]);
			long sum=0;
			for(int i=0;i<n;i++)
				sum+=dp[i][k];
			System.out.println(sum);
		}
	}
}
