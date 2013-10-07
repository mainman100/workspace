package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class MaximumSum {

	static int size = 1 << 17;
	static int[][] tree = new int[1 << 18][2];
	static int[] dum = { -1, -1 };
	static StreamTokenizer st;
	static int n;
	static int a, b, c, d, max1, max2;
	static int[] arr;

	private static int r() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	private static char c() throws IOException {
		st.nextToken();
		return st.sval.charAt(0);
	}

	public static void main(String[] args) throws IOException {
		StringBuffer out = new StringBuffer();
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(
				System.in)));
		n = r();
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = r();
		construct(1, 0, size - 1);
		int q = r();
		while (q-- > 0) {
			char c = c();
			int x = r();
			int y = r();
			if (c == 'U')
				update(x - 1, y);
			else {
				int[] p = query(1, x - 1, y - 1, 0, size - 1);
				out.append((p[0] + p[1]) + "\n");
			}
		}
		System.out.println(out);
	}

	static void construct(int index, int i, int j) {
		if (i == j) {
			tree[index][0] = (i < n ? arr[i] : -1);
			return;
		}
		construct(2 * index, i, (i + j) / 2);
		construct(2 * index + 1, (i + j) / 2 + 1, j);
		a = tree[index << 1][0];
		b = tree[index << 1][1];
		c = tree[(index << 1) + 1][0];
		d = tree[(index << 1) + 1][1];
		if (a > c) {
			max1 = a;
			max2 = Math.max(b, c);
		} else {
			max1 = c;
			max2 = Math.max(d, a);
		}
		tree[index][0] = max1;
		tree[index][1] = max2;

	}

	static void update(int index, int val) {
		index += size;
		tree[index][0] = val;
		index >>= 1;

		while (index > 0) {
			a = tree[index << 1][0];
			b = tree[index << 1][1];
			c = tree[(index << 1) + 1][0];
			d = tree[(index << 1) + 1][1];
			if (a > c) {
				max1 = a;
				max2 = Math.max(b, c);
			} else {
				max1 = c;
				max2 = Math.max(d, a);
			}
			tree[index][0] = max1;
			tree[index][1] = max2;
			index >>= 1;
		}
	}

	static int[] query(int index, int i, int j, int b, int e) {
		if (i > e || j < b)
			return dum;
		if (i <= b && j >= e)
			return tree[index];
		int mid = (b + e) / 2;
		int[] qLeft = query(2 * index, i, j, b, mid);
		int[] qRight = query(2 * index + 1, i, j, mid + 1, e);
		a = qLeft[0];
		b = qLeft[1];
		c = qRight[0];
		d = qRight[1];
		if (a > c) {
			max1 = a;
			max2 = Math.max(b, c);
		} else {
			max1 = c;
			max2 = Math.max(d, a);
		}
		return new int[] { max1, max2 };
	}
}
