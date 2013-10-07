package acm;

public class Matrix {
	public static long[][] mult(long[][] a, long[][] b, long m) {
		long[][] c = new long[a.length][b[0].length];
		for (int i = 0; i < c.length; i++)
			for (int j = 0; j < c[0].length; j++)
				for (int k = 0; k < a[0].length; k++)
					c[i][j] = (c[i][j] + (a[i][k] * b[k][j]) % m) % m;
		return c;
	}

	public static long[][] powRecursive(long[][] a, int pow, long m) {
		if (pow == 1)
			return a;
		if (pow % 2 == 0) {
			long[][] b = mult(a, a, m);
			return powRecursive(b, pow / 2, m);
		} else {
			long[][] b = mult(a, a, m);
			return mult(powRecursive(b, pow / 2, m), a, m);
		}
	}

	public static long[][] powerIterative(long[][] a, long pow, long m) {
		int n = a.length;
		long[][] res = new long[n][n];
		for (int i = 0; i < n; i++)
			res[i][i] = 1;
		while (pow > 0) {
			if ((pow & 1) != 0)
				res = mult(res, a, m);
			a = mult(a, a, m);
			pow = pow >> 1;
		}
		return res;
	}
}
