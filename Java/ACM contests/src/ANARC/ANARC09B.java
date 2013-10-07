package ANARC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ANARC09B {

	public static long gcd(long a,long b)
	{
		if(b==0)
			return a;
		return gcd(b,a%b);
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String[] d=st.readLine().split(" ");
			long w=Long.parseLong(d[0]);
			long h=Long.parseLong(d[1]);
			if(w==0&&h==0)
				break;
			long sq=(w*h)/gcd(w,h);
			System.out.println((sq/w)*(sq/h));
		}
	}
}
