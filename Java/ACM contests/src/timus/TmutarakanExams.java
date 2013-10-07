package timus;

import java.util.Scanner;

public class TmutarakanExams {

	static int[] primes;
	static int count;
	static long tot;
	static int n;
	static int MAX;
	static int setSize;
	static {
		primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
				43, 47 };
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		setSize = sc.nextInt();
		MAX = sc.nextInt();
		int max = MAX / setSize;
		for (; n < primes.length; n++) {
			if (primes[n] > max)
				break;
		}
		for(int size=1;size<=n;size++)
			loop(n,size);
		System.out.println(Math.min(tot, 10000));
	}
	public static long choose(int n, int r) {
		double ans = 1.0;
		for (int i = 0; i < r; ++i)
			ans = ans * (n - i) / (i + 1);
		return (long) ans;
	}
	public static void loop(int n, int k) {
		int s = (1 << k) - 1;
		while ((s & (1 << n)) == 0) {
			// Here you have the bitmask (s)
			addMask(s,k);
			int lo = s & ~(s - 1); // lowest one bit
			int lz = (s + lo) & ~s; // lowest zero bit above lo
			s |= lz; // add lz to the set
			s &= ~(lz - 1); // reset bits below lz
			if (lo == 0)
				break;
			s |= (lz / lo / 2) - 1;
		}
	}
	static void addMask(int s,int k)
	{
		int mult=1;
		for(int i=0;i<n;i++)
			if(((1<<i)&s)!=0)
				mult*=primes[i];
		int c=MAX/mult;
		if(c<setSize)
			return;
		tot+=k%2==1?choose(c, setSize):-choose(c, setSize);
	}
}
