package solved;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class Cows
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
	long n1,n2,n3,total,doors;
	double prob1,prob2,res;
	while(true)
	{
		n1=readInt();
		if(n1==-1)
			break;
		n2=readInt();
		n3=readInt();
		total=n1+n2-n3-1;
		doors=n1+n2;
		prob1=((n1*n2*1.0)/(doors*total));
		prob2=((n2*(n2-1)*1.0)/(doors*total));
		res=prob1+prob2;
		System.out.printf("%.5f",res);
		System.out.println();
	}
	}
}
