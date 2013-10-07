package upiic;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;

public class Names {

	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	private static String readString()throws Exception
	{
 	   st.nextToken();
 	   return  st.sval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	int n=readInt();
	String word1,word2;
	char a='a',e='e',o='o',u='u',i='i';
	for(int k=0;k<n;k++)
	{
		word1=readString();
		word2=readString();
		if(word1.length()!=word2.length())
			out.append("No\n");
		else
		{
			for(int j=0;j<word1.length();j++)
			{
				char c1=word1.charAt(j);
				char c2=word2.charAt(j);
				if(c1!=c2)
				{
					if((c1!=a&&c1!=e&&c1!=o&&c1!=u&&c1!=i)||(c2!=a&&c2!=e&&c2!=o&&c2!=u&&c2!=i))
					{
						out.append("No\n");
						break;
					}
				}
				if(j==word1.length()-1)
					out.append("Yes\n");
			}
		}
	}
	System.out.print(out);
	
	}
}
