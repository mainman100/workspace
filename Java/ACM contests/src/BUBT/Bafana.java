package BUBT;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class Bafana {


	static StreamTokenizer st;
	private static int ni()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=ni();
		for(int i=1;i<=t;i++)
		{
			int n=ni();
			int k=ni();
			int p=ni();
			int res=(p+k)%n;
			if(res==0)
				res=n;
			System.out.println("Case "+i+": "+res);
		}
	
	}
}
