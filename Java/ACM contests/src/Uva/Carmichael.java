package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Carmichael {

	static int MAX=65000;
	static boolean isPrime[] = new boolean[MAX + 1];
	public static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		sieve();
		while(true)
		{
			int n=Integer.parseInt(st.readLine());
			if(n==0)
				break;
			if(isPrime[n])
			{
				System.out.println(n+" is normal.");
				continue;
			}
			boolean carm=true;
			for(int i=2;i<n&&carm;i++)
			{
				int rem=modPower(i,n,n);
				if(rem!=i)
					carm=false;
			}
			if(carm)
				System.out.println("The number "+n+" is a Carmichael number.");
			else
				System.out.println(n+" is normal.");
		}
	}
	public static int modPower(int a,int pow,int mod)
	{
		int res=1;
		a%=mod;
		while(pow>0)
		{
			if((pow&1)==1)
				res=(res*a)%mod;
			a=(a*a)%mod;
			pow>>=1;
		}
		return res;
	}
}
