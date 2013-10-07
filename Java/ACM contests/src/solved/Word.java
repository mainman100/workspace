package solved;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class Word
{


	static StreamTokenizer st;
	private static String readString()throws Exception
	{
 	   st.nextToken();
 	   System.out.println(st.ttype);
 	   return  st.sval;
	}
	public static void main (String[]args)throws Exception
		{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		String dicto="";
		String word;
		while(!(word=stdin.readLine()).equals("#"))
			dicto+=word+" ";
		String[]dic=dicto.split(" ");
		String puzz="";
		String []split;
		while(!(word=stdin.readLine()).equals("#"))
		{
			split=word.split(" ");
			for(int i=0;i<split.length;i++)
				puzz+=split[i];
			puzz+=" ";
		}
		String[] puzzle=puzz.split(" ");
		for(int i=0;i<puzzle.length;i++)
		{
			int count=0;
			for(int j=0;j<dic.length;j++)
			{
				word=puzzle[i];
				dicto=dic[j];
				for(int k=0;k<dicto.length();k++)
				{
					if(!word.contains(dicto.charAt(k)+""))
						break;
					word=word.replaceFirst(dicto.charAt(k)+"","");
					if(k==dicto.length()-1)
						count++;
				}
			}
			out.append(count+"\n");
		}
		System.out.print(out);
	}
}
