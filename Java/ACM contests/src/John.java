import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class John {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(st.readLine());
		for (int j = 1; j <= t; j++) {
			boolean allOne = true;
			int n = Integer.parseInt(st.readLine());
			int sum = 0;
			StringTokenizer tok = new StringTokenizer(st.readLine());
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(tok.nextToken());
				if (a != 1)
					allOne = false;
				sum = sum ^ a;
			}
			String sol = "";
			if (allOne) {
				if (n % 2 == 0)
					sol = "John";
				else
					sol = "Brother";
			}
			else
			{
				if (sum == 0)
					sol = "Brother";
				else
					sol = "John";				
			}
			System.out.println(sol);
		}
	}
}
