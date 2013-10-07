package solved;


import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class Carry
{
	static StreamTokenizer st;
	private static long readInt()throws Exception
	{
	    st.nextToken();
	    if(st.ttype==StreamTokenizer.TT_EOF)
	    	return -1;
	    return (long)st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	long n1,n2,res=0;
	while(true)
	{
		n1=readInt();
		if(n1==-1)
			break;
		n2=readInt();
		res=n1^n2;
		out.append(res+"\n");
	}
	System.out.print(out);
	}
}
