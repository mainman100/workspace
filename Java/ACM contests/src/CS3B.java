public class CS3B {

	public static int myRec(int i) {
		return f1(1, i);
	}

	public static int f1(int i, int j) {
		if (i >= j)
			return 1;
		int sum = 0;
		for (int root = i; root <= j; root++)
			sum += f1(i, root - 1) * f1(root + 1, j);
		return sum;
	}

	public static int f2(int n) {
		if (n <= 1)
			return 1;
		int sum = 0;
		for (int root = 1; root <= n; root++)
			sum += f2(root - 1) * f2(n - root);
		return sum;
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println(myRec(i) + " , " + f2(i));
		}
	}
}
