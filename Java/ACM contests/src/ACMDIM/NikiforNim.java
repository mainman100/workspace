package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class NikiforNim {

	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		BigInteger n=new BigInteger(st.readLine());
		int r=n.mod(new BigInteger("3")).intValue();
		if(r==0)
			System.out.println(2);
		else
			System.out.println(1+"\n"+r);
	}
}
