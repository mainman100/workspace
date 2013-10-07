package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EPLAIN {

	public static boolean isPal(String s)
	{
		int i=0,j=s.length()-1;
		while(i<j)
		{
			if(s.charAt(i)!=s.charAt(j))
				return false;
			i++;
			j--;
		}

		return true;
	}
	public static String longestPail(String t)
	{
		StringBuffer out=new StringBuffer(t);
		out.reverse();
		String p=out.toString();
		//PreProcess------------------
		int M=p.length();
		int[]b=new int[M];
		int j=-1;
		b[0]=-1;
		for(int i=1;i<M;i++)
		{
			while(j>-1&&p.charAt(i)!=p.charAt(j+1))
				j=b[j];
			if(p.charAt(i)==p.charAt(j+1))
				j++;
			b[i]=j;
		}
		
		//Search-----------------------
		j=-1;
		for(int i=0;i<M;i++)
		{
			while(j>-1&&p.charAt(j+1)!=t.charAt(i))
				j=b[j];
			if(p.charAt(j+1)==t.charAt(i))
				j++;
		}
		String add=p.substring(j+1);
		return t+add;
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		while(true)
		{
			String test=st.readLine();
			if(test==null||test.length()==0)
				break;
			out.append(longestPail(test)+"\n");
		}
		System.out.print(out);
	}
}
