package acm;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Mathimatics {

	static long[][] comb;

	/*
	 * n!
	 */
	public static long fact(int n) {
		long res = 1L;
		for (int i = 2; i <= n; i++)
			res *= i;
		return res;
	}

	/*
	 * nPr
	 */
	public static long perm(int n, int r) {
		long res = 1L;
		for (int i = 0; i < r; i++)
			res *= n - i;
		return res;
	}

	/*
	 * nCr
	 */
	public static long choose(int n, int r) {
		double ans = 1.0;
		for (int i = 0; i < r; ++i)
			ans = ans * (n - i) / (i + 1);
		return (long) ans;
	}

	public static void comb(int n, int r) {
		comb = new long[n + 1][r + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= Math.min(r, i); j++)
				if (j == i || j == 0)
					comb[i][j] = 1;
				else
					comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];

	}

	public static void combmod(int n, int r, int mod) {
		comb = new long[n + 1][r + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= r; j++)
				if (j == i || j == 0)
					comb[i][j] = 1;
				else
					comb[i][j] = (comb[i - 1][j] % mod + comb[i - 1][j - 1]
							% mod)
							% mod;

	}

	public static void permutation(int[] x, int i) {
		if (i == x.length - 1) {
			for (int j = 0; j < x.length; j++)
				System.out.print(x[j] + (j == i ? "\n" : " "));
			return;
		}
		int temp = x[i];
		for (int j = i; j < x.length; j++) {
			x[i] = x[j];
			x[j] = temp;
			permutation(x, i + 1);
			x[j] = x[i];
			x[i] = temp;
		}
	}

	public static int[] grundyNum(int n, int[] moves) {
		int[] res = new int[n + 1];
		res[0] = 0;
		HashSet<Integer> prevGrund = new HashSet<Integer>();
		for (int i = 1; i <= n; i++) {
			for (int j : moves)
				if (i - j >= 0)
					prevGrund.add(res[i - j]);
			int num = 0;
			while (prevGrund.contains(num))
				num++;
			res[i] = num;
			prevGrund.clear();
		}
		return res;
	}

	private static boolean f(int x) {// binary search
		return true;
	}

	public static int binarySearch(int a, int b) {
		// Always f(a)=false, f(b)=true..., and there is continuous falses then
		// trues in the array
		/*
		 * For doubles, while((b-a)>EPSILON) , or for(int i=0;i<Iterations;i++)
		 */
		while (b > a + 1) {
			int c = a + (b - a) / 2;
			if (!f(c))
				a = c;
			else
				b = c;
		}
		return a;
	}

	public static int modularExponentiateIter(int base, int exp, int m) {
		int res = 1;
		base %= m;
		while (exp > 0) {
			if ((exp & 1) == 1)
				res = (res * base) % m;
			base = (base * base) % m;
			exp = exp >> 1;
		}
		return res;
	}

	public static int modularExponentiateRec(int base, int exp, int m) {
		if (exp == 1)
			return base % m;
		if (exp == 0 || base == 1)
			return 1;
		if (base >= m)
			return modularExponentiateRec(base % m, exp, m);
		if (exp % 2 == 0)
			return modularExponentiateRec(base * base, exp / 2, m);
		return (modularExponentiateRec(base * base, exp / 2, m) * base) % m;
	}

	/*
	 * O(n loglogn) by the way the sieve idea can be applied in many cases for
	 * example to find the number of divisors of integers in a range you can do
	 * sieve that given number i, add 1 to all its multiples, and i ranges from
	 * 1 till n, keep in mind that the speed of sieve comes from that it
	 * includes sum of repicols of numbers, which is normally logarithmec, (ln
	 * n, ln ln n)
	 */
	static int MAX;
	static boolean isPrime[] = new boolean[MAX + 1];

	public static void sieve() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i * i <= MAX; i++)
			if (isPrime[i])
				for (int j = i * i; j <= MAX; j += i)
					isPrime[j] = false;
	}

	/*
	 * O(log (max(a,b)))
	 */
	public static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	/*
	 * O(log (max(a,b))) returns triple (x,y,gcd(a,b)) such that ax+by=gcd(a,b)
	 */
	public static int[] extendedGCD(int a, int b) {
		if (b == 0)
			return new int[] { 1, 0, a };
		int[] res = extendedGCD(b, a % b);
		int x = res[0];
		int y = res[1];
		return new int[] { y, x - y * (a / b), res[2] };
	}

	/*
	 * Returns the exponent of the prime p in the integer factorization of n!
	 */
	public static int factorialFacotrs(long n, long prime) {
		int res = 0;
		for (long p = prime; p <= n; p *= prime)
			res += n / p;
		return res;
	}

	/*
	 * Number of positive divisors , O(sqrt(n))
	 */
	public static int numDivisors(int n) {
		int c = 0;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				c++;
			// account for n/i just if i !=n/i
			if (i * i != n)
				c++;
		}
		return c;
	}

	/*
	 * Returns the prime factorization of n, O(sqrt(N)))
	 */
	public ArrayList<Integer> factorize(int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				while (n % i == 0) {
					res.add(i);
					n /= i;
				}
			}
		}
		if (n > 1)
			res.add(n);
		return res;
	}

	/*
	 * Euler totient function; returns the number of positive integers co prime
	 * to n that are <n
	 */
	public static int phi(int n) {
		// final result=n*(1-1/p1)*(1-1/p2)...
		int res = n;
		for (int i = 2; i * i <= 2; i++) {
			if (n % i == 0)
				res -= res / i;
			while (n % i == 0)
				n /= i;
		}
		if (n > 1)
			res -= res / n;
		return res;
	}

	/*
	 * Solves the equation ax+by=c, in case it has no solution, the 3rd cell in
	 * the array will be 0 , otherwise it will return a triple (x,y,gcd(a,b))
	 * There are infinite many solutions on the form : x=x+ k* b/gcd, y=y- k*
	 * a/gcd for any integer k
	 */
	public static int[] solveLinearEquation(int a, int b, int c) {
		int[] cof = extendedGCD(a, b);
		int gcd = cof[2];
		if (c % gcd != 0)// no solution
			return new int[] { 0, 0, 0 };
		int mult = c / gcd;
		return new int[] { cof[0] * mult, cof[1] * mult, gcd };
	}

	/*
	 * Solves the set of linear congurences : x=rem1 (mod m1),x=rem2 (mod
	 * m2),.....,x=remn (mod mn),where gcd(mi,mj)=1 if i!=j .it returns the
	 * unique solution module m1*m2*....*mn O(n*log(m1*m2*...*mn))
	 */
	public static int solveChineseRemainder(int[] rems, int[] mods) {
		int M = 1;
		for (int mod : mods)
			M *= mod;
		int N;
		int[] coff;
		int res = 0, mod;
		for (int i = 0; i < mods.length; i++) {
			mod = mods[i];
			N = M / mod;
			coff = extendedGCD(N, mod);
			int a = coff[0];
			res = (((res + a * N * rems[i] % M) % M) + M) % M;
		}
		return res;
	}

	/*
	 * solves ax=1(mod n), by getting ax+yn=1 if there is no solution, this
	 * returns 0 x is the unique solution mod n all solutions are the class of
	 * integers congruent. to x mod n
	 */
	public static int modularInverse(int a, int n) {
		int[] coff = extendedGCD(a, n);
		if (coff[2] != 1)
			return 0;
		return (coff[0] + n) % n;
	}

	/*
	 * loop over all combinations of size k form a set of size n drawback:
	 * limited to sets of size up to 64, need to use array of boolean "or longs"
	 * in case the set size is more
	 */
	public static void combIter(int n, int k) {
		int s = (1 << k) - 1;
		while ((s & (1 << n)) == 0) {
			// Here you have the bitmask (s)
			int lo = s & ~(s - 1); // lowest one bit
			int lz = (s + lo) & ~s; // lowest zero bit above lo
			s |= lz; // add lz to the set
			s &= ~(lz - 1); // reset bits below lz
			if (lo == 0)
				break;
			s |= (lz / lo / 2) - 1;
		}
	}

	/**
	 * loop over combinations of size k from a set "array" of size n, this one
	 * is recursive, and if you will just perform multiplication or similar
	 * thing on the elements of the subset of size k
	 * 
	 * @param index
	 *            current index in the set
	 * @param k
	 *            the required size of the combination
	 * @param comb
	 *            indices of the taken combination:but if you are just going to
	 *            for example multiply,add the elements , then you can have an
	 *            accumulator variable and remove the comb
	 */
	public static void combRec1(int index, int k, ArrayList<Integer> comb) {
		// you cannot go further
		if (index > MAX)
			return;
		// you have a combination of k elements
		if (comb.size() == k) {
			// do something with the combination
			return;
		}
		// we will either take the current element or not, thus backtracking
		comb.add(index);
		combRec1(index + 1, k + 1, comb);
		comb.remove(new Integer(index));
		combRec1(index + 1, k, comb);
	}

	/**
	 * loop over all combinations from size 1 to n, in an increasing order of
	 * combination size, so you can just stop at the size you want if you want
	 * to use it in inclusion-exclusion, just get the sign from comb, or include
	 * it as a parameter in case you use an accumulator instead of comb
	 * 
	 * this is a simulation of k for loops to generate all k combinations
	 * example: to generate all combinations of size 2 from an array of size n
	 * 
	 * for(int i=0;i<n;i++) for(int j=i+1;j<n;j++) //here you have combinatin
	 * (i,j)
	 * 
	 * for example usage , have a look at tomek code
	 * http://www.topcoder.com/stat
	 * ?c=problem_solution&rm=131117&rd=4770&pm=2342&cr=144400
	 * 
	 * and to my solution to problem BottlesOnShelf,Member SRM 491, DIV II level
	 * 3
	 * 
	 * @param index
	 *            : chose an element from [index,lastElement] inclusive and
	 *            continue
	 * @param comb
	 *            denotes the taken elements till now
	 *            "and hence they are a combination"
	 */
	public static void combRec2(int index, ArrayList<Integer> comb) {

		// do something with the combination "except for size 0"
		for (int i = index; i < MAX; i++) {
			comb.add(i);
			combRec2(i + 1, comb);
			comb.remove(new Integer(i));
		}
	}

	/*
	 * returns the intersection of a set of intervals, if the intersection is
	 * empty , then ret[0]>ret[1]
	 */
	public static int[] intersection(int[][] intervals) {
		int start = Integer.MIN_VALUE, end = Integer.MAX_VALUE;
		for (int i = 0; i < intervals.length; i++) {
			start = Math.max(start, intervals[i][0]);
			end = Math.min(start, intervals[i][1]);
			if (start > end)
				return new int[] { 1, 0 };
		}
		return new int[] { start, end };
	}

	public static void main(String[] args) {
		int[] rems = { 1, 2, 0 };
		int[] mods = { 4, 25, 77 };
		System.out.println(solveChineseRemainder(rems, mods));
	}
}
