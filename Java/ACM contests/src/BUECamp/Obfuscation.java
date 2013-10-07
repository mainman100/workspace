package BUECamp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Obfuscation {
	
	
	static String s;
	static String []dic;
	static Integer[]dp;
	static String []path;
	static LinkedList<String> dec;
	static StringBuffer out=new StringBuffer();
	public static void main(String[]args) throws  IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(stdin.readLine());
		for(int k=0;k<t;k++)
		{
			s=stdin.readLine();
			int n=Integer.parseInt(stdin.readLine());
			dic=new String[n];
			for(int i=0;i<n;i++)
				dic[i]=stdin.readLine();
			dp=new Integer[s.length()+1];
			path=new String[s.length()+1];
			int count=solve(0);
			if(count==0)
			{
				out.append("impossible\n");
			}
			else if(count==1)
			{
				String res="";
				int i=0;
				while(i<s.length())
				{
					res+=path[i]+" ";
					i+=path[i].length();
				}
				res=res.substring(0,res.length()-1);
				out.append(res+"\n");
			}
			else
				out.append("ambiguous\n");
		}
		System.out.print(out);
	}
	public static int solve(int i)
	{
		if(dp[i]!=null)
			return dp[i];
		if(i==s.length())
			return 1;
		int count=0;
		for(String word:dic)
			if(i+word.length()<=s.length())
			{
				String sub = s.substring(i,i+word.length());
				if(ang(sub,word))
				{
					int temp=solve(i+word.length());
					count+=temp;
					if(count>1)
					{
					//	System.out.println(i);
						return dp[i]=2;						
					}
					if(temp==1)
						path[i]=word;				
				}
			}
		return dp[i]=count;
	}
	public static boolean ang(String s1,String s2)
	{
		int l=s1.length();
		if(s1.charAt(0)!=s2.charAt(0))
			return false;
		if(l==1)
			return true;
		if(s1.charAt(l-1)!=s2.charAt(l-1))
			return false;
		if(l==2)
			return true;
		char[]c1=s1.substring(1,l-1).toCharArray();
		char[]c2=s2.substring(1,l-1).toCharArray();
		Arrays.sort(c1);
		Arrays.sort(c2);
		for(int i=0;i<c1.length;i++)
			if(c1[i]!=c2[i])
				return false;
		return true;
	}
}
