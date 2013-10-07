import java.util.HashSet;
import java.util.Scanner;

public class C {

	static final int parts = 20;

	public static void main(String[] args) {
		long A, B, C;
		Scanner sc = new Scanner(System.in);
		A = sc.nextLong();
		B = sc.nextLong();
		C = sc.nextLong();

		f(A, B, C);

	}

	public static void f(long a, long b, long c) {
		int max = 2000000 / parts;
		long[] arr = new long[parts];
		arr[0] = 1;
		HashSet<Long> set = new HashSet<Long>(max);
		long prev = 1, x = 0;
		// first check if there is a an occurrence of two equal elements in any
		// of the parts
		for (int i = 0; i < parts; i++) {
			arr[i] = prev;
			set.clear();
			set.add(arr[i]);
			for (int j = 1; j < max; j++) {
				x = (a * prev + prev % b) % c;
				prev = x;
				if (set.contains(x)) {
					System.out.println(i * max + j);
					return;
				}
				set.add(x);
			}
		}
		// check each two parts to see if the occurrence is in these two
		/*
		 * for (int i = 0; i < parts; i++) { set.clear(); prev = arr[i];
		 * set.put(prev, i * max); for (int j = 1; j < max; j++) { x = (a * prev
		 * + prev % b) % c; prev = x; set.put(x, i * max + j); } for (int j = 0;
		 * j < i * max; j++) { prev = arr[0]; if (set.containsKey(prev)) {
		 * System.out.println(set.get(prev)); return; } x = (a * prev + prev %
		 * b) % c; prev = x; if (set.containsKey(x)) {
		 * System.out.println(set.get(x)); return; } }
		 * 
		 * }
		 */
		for (int i = 0; i < parts; i++) {
			for (int j = 0; j < i; j++) {
				prev = arr[j];
				set.clear();
				set.add(prev);
				for (int k = 1; k < max; k++) {
					x = (a * prev + prev % b) % c;
					prev = x;
					set.add(x);
				}
				prev = arr[i];
				if (set.contains(prev)) {
					System.out.println(i * max);
					return;
				}

				for (int k = 1; k < max; k++) {
					x = (a * prev + prev % b) % c;
					prev = x;
					if (set.contains(x)) {
						System.out.println(i * max + k);
						return;
					}
				}
			}

		}
		System.out.println(-1);
	}
}