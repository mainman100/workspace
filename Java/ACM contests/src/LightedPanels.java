public class LightedPanels {
	boolean[][] b;
	int W, H;

	public int minTouch(String[] board) {
		boolean[][] original = new boolean[board.length][board[0].length()];
		b = new boolean[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length(); j++) {
				char c = board[i].charAt(j);
				if (c == '*')
					original[i][j] = true;
				else
					original[i][j] = false;
			}
		W = b[0].length;
		H = b.length;
		int n = b.length + b[0].length - 1;
		int MAX = 1 << n;
		int res = 65;
		for (int i = 0; i < MAX; i++) {
			for (int x = 0; x < H; x++)
				for (int y = 0; y < W; y++)
					b[x][y] = original[x][y];
			String turn = toBin(i);
			for (int x = H - 1; x >= 0; x--) {
				int bit = turn.charAt(H - x - 1) - '0';
				if (bit == 1)
					toggle(x, 0);

			}
			for (int y = 1; y < W; y++) {
				int bit = turn.charAt(y + H - 1) - '0';
				if (bit == 1)
					toggle(0, y);
			}
			int press = 0;
			for (int k = 0; k < turn.length(); k++)
				if (turn.charAt(k) == '1')
					press++;
			res = Math.min(res, solve() + press);
		}
		if (res == 65)
			return -1;
		return res;
	}

	public String toBin(int n) {
		String bin = Integer.toBinaryString(n);
		while (bin.length() < W + H - 1)
			bin = "0" + bin;
		return bin;
	}

	public int solve() {
		int sum = 0;
		for (int i = 1; i < H; i++)
			for (int j = 1; j < W; j++) {
				if (!b[i - 1][j - 1]) {
					toggle(i, j);
					sum++;
				}
			}
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if (!b[i][j])
					return 65;
		return sum;
	}

	public void toggle(int i, int j) {
		for (int dx = -1; dx <= 1; dx++)
			for (int dy = -1; dy <= 1; dy++) {
				int x = i + dx;
				int y = j + dy;
				if (x < 0 || x >= H || y < 0 || y >= W)
					continue;
				b[x][y] = !b[x][y];
			}
	}
}