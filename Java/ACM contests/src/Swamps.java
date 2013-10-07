import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Swamps {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(st.readLine());
		int x = 0, y = 0, z = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer tok = new StringTokenizer(st.readLine());
			char c = tok.nextToken().charAt(0);
			int move = Integer.parseInt(tok.nextToken());
			if (c == 'X')
				x += move;
			else if (c == 'Y')
				y += move;
			else if (c == 'Z')
				z += move;
		}
		int[] res = { x, y, z };
		min(res);
		char[] c = { 'X', 'Y', 'Z' };
		int count = 0;
		for (int i : res)
			if (i != 0)
				count++;
		System.out.println(count);
		for (int i = 0; i < 3; i++)
			if (res[i] != 0)
				System.out.println(c[i] + " " + res[i]);
	}

	public static void min(int[] a) {
		int mind = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			int d = (i == 1 ? -a[i] : a[i]);
			int count = Math.abs(d - a[0]) + Math.abs(-d - a[1])
					+ Math.abs(d - a[2]);
			if (count < min) {
				mind = d;
				min = count;
			}
		}
		a[0] = mind - a[0];
		a[1] = -mind - a[1];
		a[2] = mind - a[2];
	}
}
