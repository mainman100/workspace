public class Seq {

	public static int f(int n) {
		if (n == 0)
			return 0;
		if ((n & (n - 1)) == 0)
			return n * n;
		int pos = 31;
		for (; pos >= 0; pos--)
			if (((1 << pos) & n) != 0)
				break;
		int pow = 1 << pos;
		int rem = n & (pow - 1);
		return (f(pow << 1) + f(rem << 1)) / 2 - f(pow - rem);
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 32; i++)
			System.out.println(i + " " + f(i));
	}
}
