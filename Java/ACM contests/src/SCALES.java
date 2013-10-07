import java.io.*;
import java.util.*;
import java.math.*;

public class SCALES {
	static int N, L, D, diff, r;
	static int[][] dp = new int[1000001][2];
	static char[] num = new char[1000001];

	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(bis));
		int cases = Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		for (int c = 1; c <= cases; c++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			diff = N - L;
			String in = br.readLine().trim();
			for (int i = 0; i < L; i++)
				num[i] = in.charAt(i);
			for (int i = 0; i < N; i++)
				for (int j = 0; j < 2; j++) {
					r = j + (i >= diff ? num[i - diff] - '0' : 0);
					if (i == 0)
						dp[i][j] = r <= 1 ? 1 : 0;
					else {
						if (r == 1)
							dp[i][j] = (dp[i - 1][1] % D + dp[i - 1][0] % D)
									% D;
						else
							dp[i][j] = dp[i - 1][j] % D;
					}
				}
			System.out.println(dp[N - 1][0]);
		}
	}

	public static int solve(int i, int f) {
		int r = f + (i >= diff ? num[i - diff] - '0' : 0);
		if (i < 0)
			return 1 - f;
		if (dp[i][f] != -1)
			return dp[i][f];
		if (r == 1)
			return dp[i][f] = (solve(i - 1, 1) % D + solve(i - 1, 0) % D) % D;
		return dp[i][f] = solve(i - 1, f);
	}
}