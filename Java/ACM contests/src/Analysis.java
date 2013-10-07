public class Analysis {

	public static void main(String[] args) {
		String s = "tradart";
		int n = s.length();
		int[][] dp = new int[n][n];
		char path[][] = new char[n][n];
		for (int i = 0; i < n; i++)
			dp[i][i] = 1;
		for (int l = 2; l <= n; l++)
			for (int i = 0; i < n - l + 1; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
					path[i][j] = 'g';
				} else if (dp[i][j - 1] > dp[i + 1][j]) {
					dp[i][j] = dp[i][j - 1];
					path[i][j] = 'l';
				} else {
					dp[i][j] = dp[i + 1][j];
					path[i][j] = 'd';
				}
			}
		String s1 = "", s2 = "";
		int i = 0, j = n - 1;
		while (i <= j) {
			if (i == j) {
				s1 = s1 + s.charAt(i);
				break;
			}
			if (path[i][j] == 'g') {
				s1 += s.charAt(i);
				s2 = s.charAt(j) + s2;
				i++;
				j--;
			} else if (path[i][j] == 'l')
				j--;
			else
				i++;
		}
		System.out.println(s1 + s2);
	}
}
