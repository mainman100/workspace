package solved;


import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class OddSum {
	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	int t=readInt();
	StringBuffer out=new StringBuffer();
	int n,m,res;
	for(int i=1;i<=t;i++)
	{
		n=readInt();
		m=readInt();
		n=n%2==0?n+1:n;
		m=m%2==0?m-1:m;
		n=n/2+1;
		m=m/2+1;
		res=m*(m+1)-n*(n-1)+n-m-1;
		out.append("Case "+i+": "+res+"\n");
		
	}
	System.out.print(out);
	}
}