package Spoj;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;

//https://www.spoj.pl/problems/ANARC08E/
public class JustGame {

	static StreamTokenizer st;
	private static int readInt()
	{
		try {
			st.nextToken();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args)
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		while(true)
		{
			int a=readInt();
			int b=readInt();
			if(a==-1&&b==-1)
				break;
			int c=a+b;
			if(a==1||b==1)
				out.append(a+"+"+b+"="+c+"\n");
			else
				out.append(a+"+"+b+"!="+c+"\n");
		}
		System.out.print(out);
	}
}
