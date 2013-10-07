package solved;


import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class diagnoal {
	static StreamTokenizer st;
	private static long readLong()throws Exception
	{
 	   st.nextToken();
 	   return (long) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	long m;
	int i=1;
	while((m=readLong())!=0)
	{
		long n=(long)Math.ceil(Math.sqrt((8*m+9)/4)+1.5);
		out.append("Case "+i+": "+n+"\n");
		i++;
	}
	System.out.print(out);
	}
}