package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Folding {

	static String s;
	static String MAX = "";
	static String[][]dp;
	public static void main(String[] args) throws IOException {

		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		s = st.readLine();
		for (int i = 0; i < 110; i++)
			MAX += "A";
		dp=new String[s.length()][s.length()];
		System.out.println(f(0, s.length() - 1));
	}

	private static boolean is(int c) {
		return c >= 1 && c <= 9;
	}

	public static String f(int i, int j) {
		if (i == j)
			return s.charAt(i) + "";
		if(dp[i][j]!=null)
			return dp[i][j];
		String min = MAX;
		for (int k = i; k < j; k++) {
			String s1 = f(i, k);
			String s2 = f(k + 1, j);
		//	System.out.println(s1 + " , " + s2);
			int c1 = s1.charAt(0) - '0';
			int c2 = s2.charAt(0) - '0';
			String temp = s1 + s2;
			if (is(c1) && is(c2) && s1.substring(1).equals(s2.substring(1)))
				temp = (c1 + c2) + s1.substring(1);
			else if (is(c1) && s1.substring(2, s1.length() - 1).equals(s2))
				temp = (c1 + 1) + s1.substring(1);
			else if (is(c2) && s1.equals(s2.substring(2, s2.length() - 1)))
				temp = (c2 + 1) + s2.substring(1);
			else
			{
				if (s1.equals(s2))
					temp = "2(" + s1 + ")";
			}

		//	System.out.println(temp);
			if (temp.length() < min.length())
				min = temp;
		}
		return dp[i][j]=min;
	}
	public static int pow(String s1,String s2)
	{
		String temp=s1;
		int i=1;
		while(s1.length()<s2.length())
		{
			s1+=temp;
			i++;
		}
		if(s1.equals(s2))
			return i;
		return 0;
	}
}
