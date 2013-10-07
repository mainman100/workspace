package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SomethingEasier {

	static int MAX=100000;
	static int size=9592;
	static int[] primes=new int[size];
	static boolean isPrime[] = new boolean[MAX + 1];
	public static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
		int c=0;
		for(int i=2;i<=MAX;i++)
			if(isPrime[i])
				primes[c++]=i;
	}
	public static boolean isPrime(int n)
	{
		if(n<=MAX)
			return isPrime[n];
		for(int i=2;i*i<=n;i++)
			if(n%i==0)
				return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		sieve();
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			int n=Integer.parseInt(st.readLine());
			if(isPrime(n))
				System.out.println(n);
			else if((n&1)==1)
			{
				if(isPrime(n-2))
					System.out.println(2+" "+(n-2));
				else
				{
					int large=0;
					for(int i=n-3;i>=2;i--)
						if(isPrime(i))
						{
							large=i;
							break;
						}
					n-=large;
					int p1=0,p2=0;
					for(int i=2;i<=n;i++)
					{
						if(isPrime(i)&&isPrime(n-i))
						{
							p1=i;
							p2=n-i;
							break;
						}
					}
					System.out.println(large+" "+p1+" "+p2);
				}
			}
			else
			{
				int p1=0,p2=0;
				for(int i=2;i<=n;i++)
				{
					if(isPrime(i)&&isPrime(n-i))
					{
						p1=i;
						p2=n-i;
						break;
					}
				}
				System.out.println(p1+" "+p2);
			}
		}
	}
}
