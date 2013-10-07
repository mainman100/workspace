package ENC10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class I {

	static long[]pow=new long[40];
	public static void main(String[] args) throws NumberFormatException, IOException {
		pow[0]=1;
		for(int i=1;i<40;i++)
			pow[i]=pow[i-1]*3;
		BufferedReader st=new BufferedReader(new FileReader("C:\\Users\\Loai_Ghoraba\\Desktop\\ENC10TestData\\I. Function\\function.in"));
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			long n=Long.parseLong(st.readLine());
			System.out.println(f(n));
		}
	}
	public static long f(long n)
	{
		int i;
		for(i=39;i>=0;i--)
			if(n/pow[i]!=0)
				break;
		long rem=n-pow[i];
		if(rem<=pow[i])
			return 2*pow[i]+rem;
		else
			return pow[i+1]+3*(rem-pow[i]);
	}
}
