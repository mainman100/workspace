package acm;

public class Strings {
	public static boolean subseq(String x, String y) {
		int i = 0, j = 0;
		while (i < x.length() && j < y.length()) {
			if (x.charAt(i) == y.charAt(j))
				i++;
			j++;
		}
		if (i == x.length())
			return true;
		return false;
	}

	public void KMPSearch(String T, String P) {
		int N = T.length();
		int M = P.length();

		// Preprocess---------------------------
		int j = -1;
		int[] b = new int[M];
		b[0] = -1;
		for (int i = 1; i < M; i++) {
			while (j > -1 && P.charAt(i) != P.charAt(j + 1))
				j = b[j];
			if (P.charAt(i) == P.charAt(j + 1))
				j++;
			b[i] = j;
		}

		// Search-------------------------------------
		j = -1;
		for (int i = 0; i < N; i++) {
			while (j > -1 && P.charAt(j + 1) != T.charAt(i))
				j = b[j];
			if (P.charAt(j + 1) == T.charAt(i))
				j++;
			if (j == M - 1) {
				System.out.println(i - M + 1);
				j = b[j];
			}
		}
	}
}
