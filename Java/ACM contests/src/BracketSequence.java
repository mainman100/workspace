import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketSequence {

	static char[] br;
	static int[][] dp;
	static int[][] ch;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		String data = st.readLine();
		if (data == null || data.length() == 0)
			return;
		br = data.toCharArray();
		n = br.length;
		dp = new int[n][n];
		ch = new int[n][n];
		f(0, n - 1);
		System.out.println(construct(0, n - 1));
	}

	private static boolean correct(int i, int j) {
		return (br[i] == '(' && br[j] == ')') || (br[i] == '[' && br[j] == ']');
	}

	private static char close(char c) {
		if (c == '(')
			return ')';
		if (c == ')')
			return '(';
		if (c == '[')
			return ']';
		return '[';
	}

	public static String construct(int i, int j) {
		if (i > j)
			return "";
		if (ch[i][j] == -2)
			return br[i] + construct(i + 1, j - 1) + br[j];
		if (ch[i][j] == -1)
			return close(br[j]) + construct(i, j - 1) + br[j];
		if (ch[i][j] == n)
			return br[i] + construct(i + 1, j) + close(br[i]);
		int k = ch[i][j];
		return construct(i, k) + construct(k + 1, j);
	}

	public static int f(int i, int j) {
		if (i > j)
			return 0;
		if (dp[i][j] != 0)
			return dp[i][j];
		int min = 1 << 30;
		if (correct(i, j)) {
			ch[i][j] = -2;
			min = Math.min(min, 2 + f(i + 1, j - 1));
		}
		if (br[j] == ')' || br[j] == ']') {
			int left = 2 + f(i, j - 1);
			if (left < min) {
				min = left;
				ch[i][j] = -1;
			}
		}
		if (br[i] == '(' || br[i] == '[') {
			int right = 2 + f(i + 1, j);
			if (right < min) {
				min = right;
				ch[i][j] = n;
			}
		}

		int mid;
		for (int k = i; k < j; k++) {
			mid = f(i, k) + f(k + 1, j);
			if (mid < min) {
				min = mid;
				ch[i][j] = k;
			}
		}
		return dp[i][j] = min;
	}

}
