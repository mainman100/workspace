import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Fibonacci {

	static BigInteger max=new BigInteger("2000000000");
	static BigInteger[]fib=new BigInteger[2002];
	static BigInteger fi,fj;
	static int i,j,n;
//	static BigInteger breakVal=0;
	static int Min,Max;
	public static void main(String[] args) throws IOException {
	//	BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
	//	StringTokenizer tok=new StringTokenizer(st.readLine());
/*		int e=Integer.parseInt(tok.nextToken());
		int fe=Integer.parseInt(tok.nextToken());
		int r=Integer.parseInt(tok.nextToken());
		int fr=Integer.parseInt(tok.nextToken());
		int n=Integer.parseInt(tok.nextToken());*/
		Scanner sc=new Scanner(System.in);
		int e=sc.nextInt();
		int fe=sc.nextInt();
		int r=sc.nextInt();
		int fr=sc.nextInt();
		n=sc.nextInt();
		if(e<r)
		{
			
			j=e;
			i=r;
			fj=new BigInteger(fe+"");
			fi=new BigInteger(fr+"");
		}
		else
		{
			j=r;
			i=e;
			fj=new BigInteger(fr+"");;
			fi=new BigInteger(fe+"");;
		}
		BigInteger low=new BigInteger("-2").multiply(max);
		BigInteger high=new BigInteger("2").multiply(max);
		BigInteger[][]fibMatrix={{BigInteger.ZERO,BigInteger.ONE},{BigInteger.ONE,BigInteger.ONE}};
		BigInteger[][]res=powerIterative(fibMatrix,i-j-1);
		BigInteger fj1=BigInteger.ONE;
		if(i==j+1)
			fj1=fi;
		else
		{
			while(low.compareTo(high)<=0)
			{
				BigInteger mid=(low.add(high)).divide(new BigInteger("2"));
				BigInteger[][]ret=mult(res, new BigInteger[][]{{fj},{mid}});
				if(ret[1][0].equals(fi))
				{
					fj1=mid;
					break;				
				}
				if(ret[1][0].compareTo(fi)>0)
					high=mid.subtract(BigInteger.ONE);
				if(ret[1][0].compareTo(fi)<0)
					low=mid.add(BigInteger.ONE);
			}	
		}
		long pow=0;
		if(n==j)
			System.out.println(fj);
		else if(n==i)
			System.out.println(fi);
		else if(n==j+1)
			System.out.println(fj1);
		else
		{
			BigInteger[][]mult={{fj},{fj1}};
			if(n>j)
				pow=n-j-1;
			else
			{
				mult=new BigInteger[][]{{fj1},{fj}};
				fibMatrix=new BigInteger[][]{{BigInteger.ZERO,BigInteger.ONE},{BigInteger.ONE,BigInteger.ZERO.subtract(BigInteger.ONE)}};
				pow=j-n;
			}
			res=mult(powerIterative(fibMatrix, pow),mult);
			System.out.println(res[1][0]);			
		}
	}
	public static BigInteger[][]powerIterative(BigInteger[][]a,long pow)
	{
		int n=a.length;
		BigInteger[][]res=new BigInteger[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				if(i==j)
					res[i][i]=BigInteger.ONE;
				else
					res[i][j]=BigInteger.ZERO;
		while(pow>0)
		{
			if((pow&1)!=0)
				res=mult(res, a);
			a=mult(a,a);
			pow=pow>>1;
		}
		return res;
	}
	public static BigInteger[][] mult(BigInteger[][] a, BigInteger[][] b) {
		BigInteger[][] c = new BigInteger[a.length][b[0].length];
		for(int i=0;i<c.length;i++)
			for(int j=0;j<c[0].length;j++)
				c[i][j]=new BigInteger("0");
		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[0].length; j++)
				for (int k = 0; k < a[0].length; k++)
					c[i][j] =c[i][j].add(a[i][k].multiply(b[k][j]));					
		return c;
	}
}
