package ANARC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Seinfeld {

	static int[]a;
	static int max=10000;
	public static void main(String[] args) throws IOException {
		StringBuffer out=new StringBuffer();
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		for(int t=1;;t++)
		{
			char[]b=st.readLine().trim().toCharArray();
			if(b[0]=='-')
				break;
			a=new int[b.length];
			for(int i=0;i<a.length;i++)
				a[i]=b[i]=='{'?1:-1;
			out.append(t+". "+greedy()+"\n");
		}
		System.out.print(out);
	}
	public static int greedy()
	{
		int res=0;
		int sum=0;
		for(int i=0;i<a.length;i++)
		{
			sum+=a[i];
			if(sum==-1)
			{
				res++;
				sum=1;
			}
			if(i==a.length-1)
				res+=sum/2;
		}
		return res;
	}
}
