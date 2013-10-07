package BUECamp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;

public class SUBGAM {

	static StreamTokenizer st;
	public static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			int n=r();
			if(n==0)
				break;
			boolean lose=true;
			for(int i=1;i<=20;i++)
			{
				if((i+r())!=21)
					lose=false;
			}
			if(n<=20)System.out.println("Carl wins.");
			else if(n==21)
				System.out.println("Carl loses.");
			else if(n%21!=0)
				System.out.println("Carl wins.");
			else
			{
				if(lose)
					System.out.println("Carl loses.");
				else
					System.out.println("Carl wins.");
			}
		}
	}
}
