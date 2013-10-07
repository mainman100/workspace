package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FairyTail {

	public static boolean isPrime(long n)
	{
		for(long i=2L;i*i<=n;i++)
			if(n%i==0)
				return false;
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		st.readLine();
		String d=st.readLine();
		while(d.length()<12)
			d+='0';
		long num=Long.parseLong(d);
		for(int i=0;;i++)
			if(isPrime(num+i))
			{
				System.out.println(num+i);
				break;
			}
	}
}
