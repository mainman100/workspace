package other;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;


public class Lunch
{
	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=readInt();
		for(int i=0;i<t;i++)
		{
			int st=readInt();
			int av=readInt();
		}
	}
	
}
