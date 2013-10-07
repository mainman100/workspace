package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SQFREE {

	static int size=664579;
	static int MAX=10000000;
	static int[]primes=new int[size];
	static {
		boolean isPrime[] = new boolean[MAX + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
		int c=0;
		for (int i = 2; i <= MAX; i++)
			if (isPrime[i])
				primes[c++]=i;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String data=st.readLine();
		}
	}
}
