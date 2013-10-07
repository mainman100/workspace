package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoodPermutation {

	static int N;
	static int[]x;
	static StringBuffer out=new StringBuffer();
	public static void rec(int i)
	{
		if(i==0)
		{
				for(int j=0;j<N;j++)
					out.append(x[j]+(j==N-1?"":" "));
				out.append("\n");
			return ;
		}
		int temp=x[i];
		for(int j=i;j>=0;j--)
		{
			if(x[j]%(i+1)!=0)
				continue;
			x[i]=x[j];
			x[j]=temp;
			rec(i-1);
			x[j]=x[i];
			x[i]=temp;
		}
	}
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(st.readLine());
		x=new int[N];
		for(int i=0;i<N;i++)
			x[i]=i+2;
		rec(N-1);
		System.out.println(out);
	}
}
