package BUECamp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Nim {

	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			int n=Integer.parseInt(st.readLine());
			if(n==0)
				break;
			int []a=new int[n];
			String[]f=st.readLine().split(" ");
			for(int i=0;i<n;i++)
				a[i]=Integer.parseInt(f[i]);
			int xor=0;
			for(int i:a)
				xor=xor^i;
			int r=0;
			for(int i:a)
				if((i^xor)<i)
					r++;
			System.out.println(r);
		}
	}
}
