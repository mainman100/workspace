package timus;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class SquareCountry {

	static final BigInteger TWO=new BigInteger("2");
	static final BigInteger FIVE=new BigInteger("5");
	static final BigInteger TEN=new BigInteger("10");

	public static void main(String[] args) {
		int n=new Scanner(System.in).nextInt();
/*		int minus=0;
		for(int i=3;i<n;i++)
			if(f(i)+1==f(i+1))
			{
				System.out.println(i);
				minus--;
			}
		System.out.println(f(n)+" "+(2*n+minus));*/
/*		for(int i=2;i<=n;i++)
		{
			if(f(i)-f(i-1)==1)
				System.out.println(i+" "+f(i));

		}*/
		System.out.println(f(n)+1);
	}
/*	public static int g(int n)
	{
		BigInteger b1=new BigInteger("5");
		BigInteger b2=new BigInteger("6");
		BigInteger c1,c2;
		BigInteger TenPow=new BigInteger("10");
		for(int i=2;i<=n;i++)
		{
			for(int j=1;j<=9;j++)
			{
				BigInteger c=new BigInteger(j+"").multiply(TenPow);
				
			}
		}
	}*/
	public static int f(int n)
	{
		BigInteger TWOPow=new BigInteger("2");
		BigInteger FIVEPow=new BigInteger("5");
		BigInteger TENPow=new BigInteger("10");

		HashSet<BigInteger> sol=new HashSet<BigInteger>();
		BigInteger[]coff;
		BigInteger ret1,ret2;
		for(int i=1;i<=n;i++)
		{
			coff=extendedGCD(TWOPow, FIVEPow);
			ret1=(coff[0].multiply(TWOPow)).mod(TENPow);
			ret1=ret1.add(TENPow).mod(TENPow);
			sol.add(ret1);
			
			ret2=(coff[1].multiply(FIVEPow)).mod(TENPow);
			ret2=ret2.add(TENPow).mod(TENPow);
			sol.add(ret2);
			
			TWOPow=TWOPow.multiply(TWO);
			FIVEPow=FIVEPow.multiply(FIVE);
			TENPow=TENPow.multiply(TEN);
		}
		BigInteger[]arr=sol.toArray(new BigInteger[0]);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		return sol.size();
	}
	public static BigInteger mult(BigInteger b1,BigInteger b2)
	{
		BigInteger res=BigInteger.ZERO;
		BigInteger a=b1;
		while(b2.compareTo(BigInteger.ZERO)>0)
		{
			if(b2.mod(TWO).equals(BigInteger.ONE))
				res=res.add(a);
			a=a.add(a);
			b2=b2.divide(TWO);
		}
		return res;
	}
	public static void test()
	{
		BigInteger FIVE=new BigInteger("5");
		BigInteger TWOPow=new BigInteger("2");
		HashSet<BigInteger> sol=new HashSet<BigInteger>();
		int n=10;
		for(int i=1;i<=n;i++)
		{
			System.out.println(i);
			System.out.println(Arrays.toString(extendedGCD(TWOPow.pow(i),FIVE.pow(i))));
		//	sol.add(solveChineseRemainder(new BigInteger[]{BigInteger.ZERO,BigInteger.ONE},new BigInteger[]{TWOPow.pow(i),FIVE.pow(i)}));
		//	sol.add(solveChineseRemainder(new BigInteger[]{BigInteger.ONE,BigInteger.ZERO},new BigInteger[]{TWOPow.pow(i),FIVE.pow(i)}));
		}
	//	System.out.println(sol.size());
	}
	public static BigInteger[] extendedGCD(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO))
			return new BigInteger[] { BigInteger.ONE, BigInteger.ZERO, a };
		BigInteger[] res = extendedGCD(b, a .mod(b));
		BigInteger x = res[0];
		BigInteger y = res[1];
		return new BigInteger[] { y, x.subtract(y .multiply((a .divide(b)))), res[2] };
	}

}
