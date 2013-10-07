package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CSUBSEQS {

	static String[]s=new String[4];
	static long[][][][]dp=new long[50][50][50][50];
	public static void main(String[]args) throws IOException
	{
		for(int i=0;i<50;i++)
			for(int j=0;j<50;j++)
				for(int k=0;k<50;k++)
					Arrays.fill(dp[i][j][k], -1);
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<4;i++)
			s[i]=st.readLine();
		System.out.println(f(s[0].length()-1,s[1].length()-1,s[2].length()-1,s[3].length()-1));
	}
	public static long f(int i,int j,int k,int m)
	{
		if(i<0||j<0||k<0||m<0)
			return 0;
		if(dp[i][j][k][m]!=-1)
			return dp[i][j][k][m];
		long res=0;
		char c=s[0].charAt(i);
		if(c==s[1].charAt(j)&&c==s[2].charAt(k)&&c==s[3].charAt(m))
		{
			res= 2*f(i-1,j-1,k-1,m-1)+1;
			int i1=i-1,j1=j-1,k1=k-1,m1=m-1;
			while(i1>-1&&s[0].charAt(i1)!=c)
				i1--;
			while(j1>-1&&s[1].charAt(j1)!=c)
				j1--;
			while(k1>-1&&s[2].charAt(k1)!=c)
				k1--;
			while(m1>-1&&s[3].charAt(m1)!=c)
				m1--;
			res-=f(i1,j1,k1,m1);
			int t1=i,t2=j,t3=k,t4=m;
			i=i1;
			j=j1;
			k=k1;
			m=m1;
			res+=(f(i-1,j,k,m)+f(i,j-1,k,m)+f(i,j,k-1,m)+f(i,j,k,m-1));
			res-=(f(i-1,j-1,k,m)+f(i-1,j,k-1,m)+f(i-1,j,k,m-1)+f(i,j-1,k-1,m)+f(i,j-1,k,m-1)+f(i,j,k-1,m-1));
			res+=(f(i-1,j-1,k-1,m)+f(i-1,j-1,k,m-1)+f(i-1,j,k-1,m-1)+f(i,j-1,k-1,m-1));
			res-=(f(i-1,j-1,k-1,m-1));	
			i=t1;
			j=t2;
			k=t3;
			m=t4;
		}
		else
		{
			res=res+f(i-1,j,k,m)+f(i,j-1,k,m)+f(i,j,k-1,m)+f(i,j,k,m-1);
			res=res-f(i-1,j-1,k,m)-f(i-1,j,k-1,m)-f(i-1,j,k,m-1)-f(i,j-1,k-1,m)-f(i,j-1,k,m-1)-f(i,j,k-1,m-1);
			res=res+f(i-1,j-1,k-1,m)+f(i-1,j-1,k,m-1)+f(i-1,j,k-1,m-1)+f(i,j-1,k-1,m-1);
			res=res-f(i-1,j-1,k-1,m-1);			
		}
		return dp[i][j][k][m]=res;
	}
	public static long endWithChar(int i,int j,int k,int m,char c)
	{
		return 0;
	}
}
