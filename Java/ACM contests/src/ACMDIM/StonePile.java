package ACMDIM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class StonePile {

	static int[]w;
	static int min;
	static int sum1;
	static int sum2;
	public static void rec(int i)
	{
		if(i==w.length)
		{
			min=Math.min(min, Math.abs(sum1-sum2));
			return;
		}
		sum1+=w[i];
		rec(i+1);
		sum1-=w[i];
		
		sum2+=w[i];
		rec(i+1);
		sum2-=w[i];
	}


	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int n=r();
		w=new int[n];
		for(int i=0;i<n;i++)
		{
			w[i]=r();
			min+=w[i];
		}
		rec(0);
		System.out.println(min);
	}
}
