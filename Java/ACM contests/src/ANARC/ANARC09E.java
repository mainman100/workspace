package ANARC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ANARC09E {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		for(int t=1;;t++)
		{
			int n0=Integer.parseInt(st.readLine());
			if(n0==0)
				break;
			int n1=3*n0;
			boolean even=n1%2==0;
			System.out.print(t+". ");
			System.out.print(even?"even ":"odd ");
			int n2=(n1+1)/2;
			int n3=3*n2;
			int n4=n3/9;
			System.out.println(n4);
		}
	}
}
