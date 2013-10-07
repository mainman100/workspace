/*package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {

	static double max;
	static int n;
	static double[][]x;
	static double W;
	static boolean[]take;
	public static void rec(int i)
	{
		if(i==n)
		{
			double w=0;
			double c=0;
			for(int j=0;j<n;j++)
				if(take[j])
				{
					w+=x[j][0];
					c+=x[j][1];
					if(w>W)
						return;
				}
			max=Math.max(max,c);
			return;
		}
		take[i]=false;
		rec(i+1);
		take[i]=true;
		rec(i+1);
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		n=Integer.parseInt(tok.nextToken());
		W=Double.parseDouble(tok.nextToken());
		x=new double[n][2];
		take=new boolean[n];
		for(int i=0;i<n;i++)
		{
			tok=new StringTokenizer(st.readLine());
			x[i][0]=Double.parseDouble(tok.nextToken());
			x[i][1]=Double.parseDouble(tok.nextToken());
		}
		rec(0);
		System.out.println(max);
	}
}
*/
package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {

	static double max;
	static int n;
	static double[][]x;
	static double W;
	static double wt,ct;
	public static void rec(int i)
	{
		if(i==n)
		{
			max=Math.max(max,ct);
			return;
		}
		rec(i+1);
		
		wt+=x[i][0];
		ct+=x[i][1];
		if(wt<=W)
			rec(i+1);
		wt-=x[i][0];
		ct-=x[i][1];
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		n=Integer.parseInt(tok.nextToken());
		W=Double.parseDouble(tok.nextToken());
		x=new double[n][2];
		for(int i=0;i<n;i++)
		{
			tok=new StringTokenizer(st.readLine());
			x[i][0]=Double.parseDouble(tok.nextToken());
			x[i][1]=Double.parseDouble(tok.nextToken());
		}
		rec(0);
		System.out.println(max);
	}
}
