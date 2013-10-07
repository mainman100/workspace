import java.util.Arrays;

public class Inversions {

	static int mod = 1000000009;
	static int[][] dp = new int[2010][2010];

	public static void main(String[] args) {
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		for (int i = 0; i <= 2000; i += 2)
			dp[0][i] = 1;
		System.out.println(countAll(100, 2000));
		for (int i = 1; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		Arrays.fill(dp[0], 0);
		for (int i = 1; i < dp.length; i++)
			dp[i][0] = 1;
		System.out.println(countInversions(100, 2000));
	}

	public static int countInversions(int n, int m) {
		int sum = 0;
		for (int i = m % 2; i <= m; i += 2)
			sum = (sum + countOne(n, i) % mod) % mod;
		return sum;
	}

	public static int countOne(int n, int m) {
		if (dp[n][m] != -1)
			return dp[n][m];
		int sum = 0;
		for (int i = Math.max(0, m - n + 1); i <= m; i++)
			sum = (sum + countOne(n - 1, i) % mod) % mod;
		return dp[n][m] = sum;
	}

	public static int countAll(int n, int m) {
		if (dp[n][m] != -1)
			return dp[n][m];
		int sum = 0;
		for (int i = Math.max(0, m - n + 1); i <= m; i++)
			sum = (sum + countAll(n - 1, i) % mod) % mod;
		return dp[n][m] = sum;
	}
}
