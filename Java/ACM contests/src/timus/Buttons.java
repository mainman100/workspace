package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Buttons {

	static int[] primes=new int[1229];
	static void sieve()
	{
		boolean []isPrime=new boolean[10001];
		Arrays.fill(isPrime, true);
		for(int i=2;i*i<=10000;i++)
		{
			if(isPrime[i])
			{
				for(int j=i*i;j<=10000;j+=i)
					isPrime[j]=false;
			}
		}
		int c=0;
		for(int i=2;i<=10000;i++)
			if(isPrime[i])
				primes[c++]=i;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		sieve();
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int k=Integer.parseInt(st.readLine());
		if(k%4==0&&k%3!=0)
		{
			System.out.println(3);
			return;
		}
		if(k%2==0)
			k/=2;
		boolean found=false;
		for(int i=1;i<primes.length;i++)
			if(k%primes[i]==0)
			{
				System.out.println(primes[i]-1);
				found=true;
				break;
			}
		if(!found)
			System.out.println(k-1);
	}
}
