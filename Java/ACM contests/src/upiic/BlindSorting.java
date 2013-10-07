package upiic;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class BlindSorting
{
	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   if(st.ttype==StreamTokenizer.TT_EOF)
 		   return -1;
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	int n;
	while((n=readInt())!=-1)
	{
		if(n==2)
			out.append("1\n");
		else
			out.append((n-1+n-3)+"\n");
	}
	System.out.print(out);
	}
}
