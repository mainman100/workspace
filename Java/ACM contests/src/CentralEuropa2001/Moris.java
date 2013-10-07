package CentralEuropa2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Moris {
	static int[] f;
	static int l;
	static String mor;
	static 	String []alph={".-","-...", "-.-.", "-.." 
		,".","..-.", "--.","....", 
		 ".."   , ".---" , "-.-"  , ".-..", 
		 "--",   "-." ,   "---"  , ".--." ,
		 "--.-"  ,".-."  , "..." ,  "-", 
		 "..-" ,  "...-" , ".--"  , "-..-" ,
		 "-.--" , "--.."};
	static String[]dic;
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		int d=Integer.parseInt(stdin.readLine().trim());
		for(int t=0;t<d;t++)
		{
			mor=stdin.readLine().trim();
			int n=Integer.parseInt(stdin.readLine().trim());
			dic=new String[n];
			for(int t2=0;t2<n;t2++)
			{
				dic[t2]="";
				String temp=stdin.readLine().trim();
				for(int k=0;k<temp.length();k++)
					dic[t2]+=alph[temp.charAt(k)-'A'];
			}
			l=mor.length();
			f=new int[l];
			Arrays.fill(f,-1);
			/*for(int i=0;i<l;i++)
			{
				for(String word:dic)
				{
					if(i>=word.length()-1){
						String sub = mor.substring(i-word.length()+1,i+1);
						if(i == word.length()-1)
							f [i]+= (sub.equals(word))?1:0;
						else
						{
							f [i] += (sub.equals(word))?f[i-word.length()]:0;
						}
					}
				}
			}*/
			System.out.println(solve(0));
		}
	}
	public static int solve(int i){
		if(i == l)
			return 1;
		if(f [i] == -1){
			f [i] = 0;
			for(String word:dic)
			{
				if(i+word.length()-1 < mor.length()){
					String sub = mor.substring(i,i+word.length());
					if(sub.equals(word))
						f [i] += solve(i+word.length());
				}
			}
		}
		return f [i];
	}
}
