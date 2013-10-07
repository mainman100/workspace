package Spoj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class ASSIGN {

	static boolean[][] like;
	static long[] dp;
	static int n;
	static int[] count = new int[1 << 20];
	static StreamTokenizer st;

	private static int r() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	private static void initCount() {
		for (int i = 0; i < (1 << 20); i++) {
			int c = 0;
			for (int j = 0; j < 20; j++)
				if (((1 << j) & i) != 0)
					c++;
			count[i] = c;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(
				System.in)));
		int t = r();
		initCount();
		while (t-- > 0) {
			n = r();
			like = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					like[i][j] = r() == 1 ? true : false;
				}
			}

			dp = new long[1 << n];
			dp[(1 << n) - 1] = 1;
			for (int j = (1 << n) - 2; j >= 0; j--) {
				int idx = count[j];
				for (int k = 0; k < n; k++) {
					if (like[idx][k] && (j & (1 << k)) == 0)
						dp[j] += dp[j | (1 << k)];
				}
			}
			System.out.println(dp[0]);
			dp = null;
			like = null;
		}
	}
}
