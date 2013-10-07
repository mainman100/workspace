import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCM {

	static int MAX = 1000001;
	static int[] phi = new int[MAX];
	static long lcm[] = new long[MAX];

	static void initPhi() {
		for (int i = 0; i < MAX; i++)
			phi[i] = i;
		for (int i = 2; i < MAX; i++)
			if (phi[i] == i) {
				for (int j = i; j < MAX; j += i)
					phi[j] -= phi[j] / i;
			}
	}

	static void initLCM() {
		for (int i = 1; i < MAX; i++)
			for (int j = i; j < MAX; j += i)
				lcm[j] += 1L * i * phi[i];
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		initPhi();
		initLCM();
		System.setIn(new FileInputStream(
				"C:\\Users\\Loai_Ghoraba\\Desktop\\ACM\\out.txt"));
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));

	}
}
