package timus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MostComplex {

	static long ret;
	static long best, comp;
	static int size = 15;
	static double max;
	static int lo, high, mid;
	static int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
			47 };

	public static void factor(long n) {
		for (int i = 2; i * i <= n; i++)
			if (n % i == 0) {
				System.out.print(i + " ");
				n /= i;
				i--;
			}
		System.out.println();
	}

	public static boolean safe(int idx, int prev1, int prev2) {
		if (idx >= 2) {
			if ((prev2 > 2)
					&& (prev1 + 2) * (prev2 - 1) > (prev2 + 1) * (prev1 + 1))
				return false;
			if (prev1 > 1
					&& ((prev1) * (prev2 + 2) > (prev2 + 1) * (prev1 + 1)))
				return false;
		}
		return true;
	}

	public static void f(long soFarDiv, long soFarNum, int idx, int prev) {
		if (soFarDiv > best || (soFarDiv == best && soFarNum < ret)) {
			best = soFarDiv;
			ret = soFarNum;
		}
		if (idx >= size)
			return;
		int pow = 1;
		double ov = 1.0 * soFarNum;
		soFarNum *= primes[idx];
		ov *= primes[idx];
		while (ov <= max) {
			if (!safe(idx + 1, pow, prev)) {
				soFarNum *= primes[idx];
				ov *= primes[idx];
				pow++;
				continue;
			}
			f(soFarDiv * (pow + 1), soFarNum, idx + 1, pow);
			soFarNum *= primes[idx];
			ov *= primes[idx];
			pow++;
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// System.setIn(new FileInputStream(
		// "C:\\Users\\Loai_Ghoraba\\Desktop\\in.txt"));
		// factor(561007802991636000L);
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(st.readLine());
	//	 long cur = System.currentTimeMillis();
		StringBuffer out=new StringBuffer();
		while (t-- > 0) {
			long n = Long.parseLong(st.readLine());
			ret = 0;
			best = 0;
			max = 1.0 * n;
			f(1, 1, 0, -1);
			out.append(ret + " " + best+"\n");
		}
		System.out.print(out);
	//	 System.out.println(System.currentTimeMillis() - cur);
	}
}
