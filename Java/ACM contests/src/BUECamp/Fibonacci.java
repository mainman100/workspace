package BUECamp;


import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class Fibonacci {

	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			int n=r();
			if(n==-1)
				break;
			
			int[][]start=new int[2][2];
			start[0][0]=1;
			start[0][1]=1;
			start[1][0]=1;
			start[1][1]=0;

			int count=n;
			LinkedList<int[][]>a=new LinkedList<int[][]>();
			while(count>0)
			{
				int pow=(int)(Math.log(count)/Math.log(2));
				int num=1<<pow;
				count-=num;
				while(pow>0)
				{
					start=mult(start,start);
					pow--;
				}
				a.add(start);
				start=new int[2][2];
				start[0][0]=1;
				start[0][1]=1;
				start[1][0]=1;
				start[1][1]=0;
			}
			int[][]res=new int[2][2];
			res[0][0]=1;
			res[0][1]=0;
			res[1][0]=1;
			res[1][1]=0;
			for(int i=0;i<a.size();i++)
				res=mult(res,a.get(i));
			System.out.println(res[0][1]);
		}
	}
	public static int[][] mult(int[][]a,int[][]b)
	{
		int ul=(a[0][0]*b[0][0]+a[0][1]*b[1][0])%10000;
		int ur=(a[0][0]*b[0][1]+a[0][1]*b[1][1])%10000;
		int dl=(a[1][0]*b[0][0]+a[1][1]*b[1][0])%10000;
		int dr=(a[1][0]*b[0][1]+a[1][1]*b[1][1])%10000;
		int[][]res=new int[2][2];
		res[0][0]=ul;
		res[0][1]=ur;
		res[1][0]=dl;
		res[1][1]=dr;
		return res;
	}
}
/*Ramy soltion
 * import java.io.*;
public class Fibonaci {
	public static void main(String[] args) throws IOException{
		BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in)) ;
		while(true)
		{
			int n = new Integer(rdr.readLine()) ;
			if(n == -1)
				return ;
			long[][]initial = new long[2][2] ;
			initial[0][0] = 1 ;
			initial[1][1] = 1 ;
			initial[0][1] = 0 ;
			initial[1][0] = 1 ;
			long[][]res = pow(n+1,initial,10000) ;
			System.out.println(res[0][1]);
		}
	}
	public static long[][]multi(long[][]a,long[][]b,int m)
	{
		long[][]res = new long[2][2] ;
		res[1][0] = ((a[1][0]*b[1][0])+(a[1][1]*b[0][0]))%m ;
		res[1][1] = ((a[1][0]*b[1][1])+(a[1][1]*b[0][1]))%m ;
		res[0][0] = ((a[0][0]*b[1][0])+(a[0][1]*b[0][0]))%m ;
		res[0][1] = ((a[0][0]*b[1][1])+(a[0][1]*b[0][1]))%m ;
		return res ;
	}
	public static long[][]pow(long n,long[][]a,int m)
	{
		if(n == 1 || n == 0)
			return a ;
		if(n%2 == 0)
		{
			long[][]b = multi(a,a,m) ;
			return pow(n/2,b,m) ;
		}
		else
		{
			long[][] b = multi(a,a,m) ;
			return multi(pow(n/2,b,m),a,m); 
		}
	}
}
*/
/* Contest solution
 * import java.io.* ;
public class fib {
    public static int[] mult(int[] a, int[] b) {
	return new int[]
	    { (a[0]*b[0]+a[1]*b[1]) % 10000, 
	      (a[0]*b[1]+a[1]*b[2]) % 10000,
	      (a[1]*b[1]+a[2]*b[2]) % 10000 } ;
    }
    public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
	while (true) {
	    String s = br.readLine() ;
	    int n = Integer.parseInt(s) ;
	    if (n < 0)
		return ;
	    int[] up = new int[] { 1, 1, 0 } ;
	    int[] r = new int[] { 1, 0, 1 } ;
	    while (n > 0) {
		if (0 != (n & 1))
		    r = mult(r, up) ;
		up = mult(up, up) ;
		n >>= 1 ;
	    }
	    System.out.println(r[1]) ;
	}
    }
}
 */
