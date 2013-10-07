package DJ1907;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CarryAdder {
	
	static long max=1L<<31;
	
	public static void main (String[]args)throws Exception
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(st.readLine());
		for(int i=0;i<t;i++)
		{
			String[] nums=st.readLine().split(" ");
			String x=nums[0];
			String y=nums[1];
			if(i!=0)
				System.out.println();
			solve(x,y);
		}
	}
	public static String toBin(long x)
	{
		String res=Long.toBinaryString(x);
		while(res.length()<31)
			res='0'+res;
		return res;
	}
/*	public static void solve(String x,String y)
	{
		long max=1L<<31;
		long a=Long.valueOf(x,2);
		long b=Long.valueOf(y,2);
		while(b!=0&&b<max)
		{
			long c=a^b;
			long d=a&b;
			a=c;
			b=2*d;	
			System.out.print(toBin(a)+" ");
			if(b<max)
				System.out.println(toBin(b));
			else
				System.out.println("overflow");
		}
	}*/
	public static void solve(String x,String y)
	{
		System.out.println(x+" "+y);
		long a=Long.valueOf(x,2);
		long b=Long.valueOf(y,2);
		while(b!=0 && b<max){
			long c=a^b;
			long d=a&b;
			a=c; b=2*d;
			System.out.print(toBin(a)+" ");
			if(b<max)
				System.out.println(toBin(b));
			else System.out.println("overflow");
		}
	}
}
