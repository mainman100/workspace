package Uva;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Hieroglyphs {

	static 	String dic="BUSPFTM"; 
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		int[]num={1,10,100,1000,10000,100000,1000000};
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			String word=st.readLine();
			if(!sorted(word)||!nine(word))
			{
				
				System.out.println("error");
				continue;
			}
			long acc=0;
			for(int i=0;i<word.length();i++)
			{
				char c=word.charAt(i);
				int pos=dic.indexOf(c);
				acc+=num[pos];
			}
			System.out.println(acc);
		}
	
	}
	public static boolean sorted(String word)
	{
		if(word.length()==1)
			return true;
		char cf=word.charAt(0);
		char ce=word.charAt(word.length()-1);
		if(cf==ce)
		{
			word=word.replaceAll(cf+"","");
			if(word.length()==0)
				return true;
			return false;
		}
		int pos1=dic.indexOf(cf);
		int pos2=dic.indexOf(ce);
		boolean acc=pos1<pos2;
		for(int i=0;i<word.length()-1;i++)
		{
			char c1=word.charAt(i);
			char c2=word.charAt(i+1);
			int j1=dic.indexOf(c1);
			int j2=dic.indexOf(c2);
			if(acc)
			{
				if(j1>j2)
					return false;
			}
			else
			{
				if(j1<j2)
					return false;
			}
		}
		return true;
			
	}
	public static boolean nine(String word)
	{
		String temp=word;
		for(int i=0;i<word.length();i++)
		{
			char c=word.charAt(i);
			temp=temp.replaceAll(c+"","");
			if(word.length()-temp.length()>9)
				return false;
			temp=word;
		}
		return true;
	}
}
