import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Function {
	static HashMap<Long, Long> f = new HashMap<Long, Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new FileReader("C:\\Users\\Loai_Ghoraba\\Desktop\\ACM\\ENC10TestData\\I. Function\\function.in"));
		BufferedReader out=new BufferedReader(new FileReader("C:\\Users\\Loai_Ghoraba\\Desktop\\ACM\\ENC10TestData\\I. Function\\function.out"));

		int t;
		t = Integer.parseInt(st.readLine());
		for (int i = 0; i < t; ++i)
		{
			long res=f(Long.parseLong(st.readLine()));
			long com=Long.parseLong(out.readLine());
			if(com!=res)
				System.out.println("err");
		}
	}

	public static long f(long n) {

		if (n < 3)
			return n + 1;
		if (f.containsKey(n))
			return f.get(n);
		else
			f.put(n, (3 - n % 3) * f(n / 3) + n % 3 * f(n / 3 + 1));
		return f.get(n);
	}
}