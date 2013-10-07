/*package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BoundedKnapsack {

	static double[][]x;
	static int[]taken;
	static int N;
	static double W;
	static double max;
	public static void rec(int i)
	{
		if(i==N)
		{
			double w=0;
			double c=0;
			for(int j=0;j<N;j++)
			{
				w+=taken[j]*x[j][0];
				c+=taken[j]*x[j][1];
			}
			if(w>W)
				return;
			max=Math.max(max, c);
			return;
		}
		for(int j=0;j<=x[i][2];j++)
		{
			taken[i]=j;
			rec(i+1);
		}
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		N=Integer.parseInt(tok.nextToken());
		W=Double.parseDouble(tok.nextToken());
		x=new double[N][3];
		taken=new int[N];
		for(int i=0;i<N;i++)
		{
			tok=new StringTokenizer(st.readLine());
			x[i][0]=Double.parseDouble(tok.nextToken());
			x[i][1]=Double.parseDouble(tok.nextToken());
			x[i][2]=Double.parseDouble(tok.nextToken());
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

public class BoundedKnapsack {

	static double[][] x;
	static int N;
	static double W;
	static double max;
	static double wt;
	static double ct;

	public static void rec(int i) {
		if (i == N) {
			max = Math.max(max, ct);
			return;
		}
		for (int j = 0; j <= x[i][2]; j++) {
			wt += j * x[i][0];
			ct += j * x[i][1];
			if(wt<=W)
				rec(i + 1);
			wt -= j * x[i][0];
			ct -= j * x[i][1];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok = new StringTokenizer(st.readLine());
		N = Integer.parseInt(tok.nextToken());
		W = Double.parseDouble(tok.nextToken());
		x = new double[N][3];
		for (int i = 0; i < N; i++) {
			tok = new StringTokenizer(st.readLine());
			x[i][0] = Double.parseDouble(tok.nextToken());
			x[i][1] = Double.parseDouble(tok.nextToken());
			x[i][2] = Double.parseDouble(tok.nextToken());
		}
		rec(0);
		System.out.println(max);
	}
}
