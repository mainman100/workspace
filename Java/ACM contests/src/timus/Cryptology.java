package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Cryptology {


	static int MAX=200000;
	static int size=15002;
	static ArrayList<Integer> primes=new ArrayList<Integer>();
	static boolean isPrime[] = new boolean[MAX + 1];
	public static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
		for(int i=2;i<=MAX;i++)
			if(isPrime[i])
				primes.add(i);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		sieve();
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			int n=Integer.parseInt(st.readLine());
			System.out.println(primes.get(n-1));
		}
	}
}
