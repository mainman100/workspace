import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MostComplex {

	static int size=1229;
	static int MAX = 10000;
	static boolean[] isPrime = new boolean[MAX+1];
	static int[]primes=new int[size];
	public static void main(String[] args) throws NumberFormatException, IOException {
		sieve();
		factor(7560);
		factor(9240);
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		long n=Long.parseLong(st.readLine());
		System.out.println(Arrays.toString(f(n)));
	}
	public static void factor(int n)
	{
		int[]pow=new int[size];
		int pos=0;
		while(n>1)
		{
			if(n%primes[pos]==0)
			{
				n/=primes[pos];
				pow[pos]++;
			}
			else
				pos++;
		}
		for(int i=0;i<=pos;i++)
			System.out.print(pow[i]+" ");
		System.out.println();
	}
	public static long[]f(long n)
	{
		int[]pow=new int[size];
		PriorityQueue<pair> q=new PriorityQueue<pair>();
		for(int i:primes)
			q.add(new pair(i, 0));			
		long num=1L;
		while(!q.isEmpty())
		{
			pair pa=q.poll();
			int p=pa.p;
			int index=Arrays.binarySearch(primes, p);
			if(num*p<=n)
			{
				num*=p;
				pow[index]++;
				q.add(new pair(p, pow[index]));
			}
		}
		int count=1;
		for(int i=0;i<size&&pow[i]!=0;i++)
			count*=(pow[i]+1);
		return new long[]{count,num};
	}
	public static void sieve() {
		int pos=0;
		Arrays.fill(isPrime, true);
		for (int i = 2; i*i<= MAX; i++)
			if (isPrime[i])
			{
				primes[pos++]=i;
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;	
			}
		for(int i=101;i<=MAX;i++)
			if(isPrime[i])
				primes[pos++]=i;
	}
	static class pair implements Comparable<pair> {
		int p;
		int e;

		public pair(int p, int e) {
			super();
			this.p = p;
			this.e = e;
		}

		public int compareTo(pair a) {
			if(e!=a.e)
				return e-a.e;
			return p-a.p;
		}
	}

}
