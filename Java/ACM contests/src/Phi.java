public class Phi {

	public static void main(String[] args) {
		int n = 100000;
		int[] phi = new int[n];
		for (int i = 0; i < n; i++)
			phi[i] = i;
		for (int i = 2; i < n; i++)
			if (phi[i] == i) {
				for (int j = i; j < n; j += i)
					phi[j] -= phi[j] / i;
			}
		for (int i = 2; i < n; i++) {
			if (phi(i) != phi[i])
				System.out.println(i + " " + phi[i] + " " + phi(i));
		}
	}

	public static int phi(int n) {
		// final result=n*(1-1/p1)*(1-1/p2)...
		int res = n;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				res -= res / i;
			while (n % i == 0)
				n /= i;
		}
		if (n > 1)
			res -= res / n;
		return res;
	}
}
