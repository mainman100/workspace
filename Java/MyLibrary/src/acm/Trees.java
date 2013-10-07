package acm;

class SegmentTree {
	static int[] tree;
	static int n;
	static int size = 1;

	public static void construct() {
		while (size < n)
			size <<= 1;
		tree = new int[2 * size];
	}

	/*
	 * Used if the node is used to store the maximum value in its range
	 */
	public static void insertMax(int index, int val) {
		index += size;
		tree[index] = val;
		index >>= 1;
		while (index > 0) {
			tree[index] = Math.max(tree[2 * index], tree[2 * index + 1]);
			index >>= 1;
		}
	}

	public static int queryMax(int index, int i, int j, int b, int e) {
		if (i > e || j < b)
			return -1;
		if (i <= b && j >= e)
			return tree[index];
		int mid = b + (e - b) / 2;
		int qLeft = queryMax(2 * index, i, j, b, mid);
		int qRight = queryMax(2 * index + 1, i, j, mid + 1, e);
		return Math.max(qLeft, qRight);
	}

	/*
	 * Used if the node saves the sum of the number of occurrences of the
	 * elements in its range
	 */
	public static void insertSum(int index, int occ) {
		index += size;
		while (index > 0) {
			tree[index] += occ;
			index >>= 1;
		}
	}

	public static long querySum(int index, int i, int j, int b, int e) {
		if (i > e || j < b)
			return 0;
		if (i <= b && j >= e)
			return (long) tree[index];
		int mid = b + (e - b) / 2;
		long qLeft = querySum(2 * index, i, j, b, mid);
		long qRight = querySum(2 * index + 1, i, j, mid + 1, e);
		return qLeft + qRight;
	}

	/*
	 * Get the index of the number that occur after kth occurrences, with kth
	 * inclusive
	 */
	public static int access(int k) {
		int start = 1;
		while (start < size) {
			start <<= 1;
			if (tree[start] < k) {
				k -= tree[start];
				start++;
			}
		}
		return start - size;
	}
}

/*
 * 2 dimensional Binary indexed tree, indices are 1-based, and reading any value
 * <=0 yields 0. All operation are in O(log n)
 */
class BIT2D {
	/*
	 * Store sum of values in the rectangle (1,1) to (x,y) inclusive, assuming
	 * we have 2d array cell[][]
	 */
	static int tree[][];
	static int max_x;
	static int max_y;

	/*
	 * Elements ranges from 1 to max_x inclusive and from 1 to max_y inclusive
	 */
	BIT2D(int x, int y) {
		max_x = x;
		max_y = y;
		tree = new int[max_x][max_y];
	}

	/*
	 * returns sum of values in the rectangle (1,1) to (x,y) inclusive, if x or
	 * y<=0, this return 0
	 */
	static int read(int x, int y) {
		int sumx = 0;
		int y1;
		while (x > 0) {
			int sumy = 0;
			y1 = y;
			while (y1 > 0) {
				sumy += tree[x][y1];
				y1 -= (y1 & -y1);
			}
			x -= (x & -x);
			sumx += sumy;
		}
		return sumx;
	}

	/*
	 * Add <code>val</code> to cell[x][y] and updates the BIT, if you want to
	 * set it to val, then call update(x,y,val-readSingle(x,y))
	 */
	static void update(int x, int y, int val) {
		int y1;
		while (x <= max_x) {
			y1 = y;
			while (y1 <= max_y) {
				tree[x][y1] += val;
				y1 += (y1 & -y1);
			}
			x += (x & -x);
		}
	}

	/*
	 * Reads sum of values in rectangle (x1,y1) and (x2,y2) inclusive
	 */
	static int read(int x1, int y1, int x2, int y2) {
		return read(x2, y2) - read(x2 - 1, y1) - read(x1, y2 - 1)
				+ read(x1 - 1, y1 - 1);
	}

	/*
	 * Reads the value at cell[x][y]
	 */
	static int readSingle(int x, int y) {
		return read(x, y, x, y);
	}
}
