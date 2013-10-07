package Uva;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class Krochanska {


	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		
	
	}
}
