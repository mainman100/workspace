package solved;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class Army
{


	static StreamTokenizer st;
	private static long readInt()throws Exception
	{
 	   st.nextToken();
 	   if(st.ttype==StreamTokenizer.TT_EOF)
 		   return -1L;
 	   return (long) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	long n1,n2,res;
	while(true)
	{
		n1=readInt();
		if(n1==-1L)
			break;
		n2=readInt();
		res=n2-n1;
		res=res<0?-1*res:res;
		out.append(res+"\n");
	}
		System.out.print(out);
	}
}
