package acm;

import java.util.Arrays;

public class DynamicProgramming {

}

class LongestIncreasingSubsequence {
	static int[] a;
	static int[] dp;

	public static int LIS() {
		dp = new int[a.length];
		Arrays.fill(dp, -1);
		int max = 0;
		for (int i = 0; i < a.length; i++)
			max = Math.max(max, f(i));
		return max;
	}

	public static int f(int i) {
		if (dp[i] != -1)
			return dp[i];
		int max = 0;
		for (int j = 0; j < i; j++)
			if (a[j] < a[i])
				max = Math.max(max, f(j));
		return dp[i] = max + 1;
	}
}
