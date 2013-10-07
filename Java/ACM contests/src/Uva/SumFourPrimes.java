package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumFourPrimes {

	static final int MAX = 10000000;
	static boolean isPrime[] = new boolean[MAX + 1];

	static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
	}

	public static void main(String[] args) throws IOException {
		sieve();
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String data = st.readLine();
			if (data == null || data.length() == 0)
				break;
			int n = Integer.parseInt(data);
			if (n <= 7)
				System.out.println("Impossible.");
			else {

				String res = "2 ";
				n -= 2;
				if ((n & 1) == 0) {
					n -= 2;
					res += "2 ";
				} else {
					n -= 3;
					res += "3 ";
				}
				for (int i = 0; i <= MAX; i++) {
					if (isPrime[i] && isPrime[n - i]) {
						res += i + " " + (n - i);
						break;
					}
				}
				System.out.println(res);
			}
		}
	}
}
