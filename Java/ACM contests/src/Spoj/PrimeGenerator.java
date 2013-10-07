package Spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PrimeGenerator {

	static int max = 31623;
	static int size = 3401;
	static boolean[] p = new boolean[max + 1];
	static int[] primes = new int[size];

	static void sieve() {
		for (int i = 0; i <= max; i++)
			p[i] = true;
		p[0] = p[1] = false;
		for (int i = 2; i * i <= max; i++)
			if (p[i]) {
				for (int j = i * i; j <= max; j += i)
					p[j] = false;
			}
		int c = 0;
		for (int i = 2; i <= max; i++)
			if (p[i])
				primes[c++] = i;
	}

	static StreamTokenizer st;

	private static int r() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	public static void main(String[] args) throws Exception {
		sieve();
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(
				System.in)));
		StringBuffer out=new StringBuffer();
		int t = r();
		while (t-- > 0) {
			int m = r(), n = r();
			if(m==1)
				m=2;
			int cnt,gab,j;
			boolean[] pr = new boolean[n - m + 1];
			for (int i = 0; i < n - m + 1; i++)
				pr[i] = true;
			for (int i = 0; i < size && primes[i] <= n; i++) {
				cnt = primes[i];
				gab=(cnt-m%cnt)%cnt;
				j=m+gab;
				if(j==cnt)
					j+=cnt;
				for (; j <= n; j += cnt) {
					pr[j - m] = false;
				}
			}
			for (int i = 0; i < n - m + 1; i++)
				if (pr[i])
					out.append((i+m)+"\n");
			out.append("\n");
		}
		System.out.print(out);
	}

}
