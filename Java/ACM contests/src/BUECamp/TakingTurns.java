package BUECamp;


import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class TakingTurns {

	static StreamTokenizer st;

	private static int r() throws Exception {
		st.nextToken();
		if (st.ttype == StreamTokenizer.TT_EOF)
			return -1;
		return (int) st.nval;
	}

	public static void main(String[] args) throws Exception {
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		while (true) {
			int n = r();
			if (n == -1)
				break;
			int[] w = new int[n];
			for (int i = 0; i < w.length; i++)
				w[i] = r();
			solve(w);
		}
	}

	public static void solve(int[] w) {
		long a=0,b=0;
		for(int i=w.length-1;i>=0;i--)
			if(b+w[i]>=a)
			{
				long t=a;
				a=b+w[i];
				b=t;
			}
		System.out.println(a+" "+b);
	}
}
