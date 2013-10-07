import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Matrix {


	static StringBuffer out=new StringBuffer();
	static int[][]mux;
	private static void row(int a,int b)
	{
		int[]row1=mux[a];
		int[]row2=mux[b];
		int temp;
		for(int i=0;i<row1.length;i++)
		{
			temp=row1[i];
			row1[i]=row2[i];
			row2[i]=temp;
		}
	}
	private static void col(int a,int b)
	{
		for(int i=0;i<mux[0].length;i++)
		{
			int temp=mux[i][a];
			mux[i][b]=mux[i][a];
			mux[i][a]=temp;
		}
	}
	private static void inc()
	{
		int[]row;
		for(int i=0;i<mux.length;i++)
		{
			row=mux[i];
			for(int j=0;j<mux.length;j++)
			{
				row[j]++;
				if(row[j]==10)
					row[j]=0;
			}
		}
	}
	private static void dec()
	{
		int[]row;
		for(int i=0;i<mux.length;i++)
		{
			row=mux[i];
			for(int j=0;j<mux.length;j++)
			{
				row[j]--;
				if(row[j]==-1)
					row[j]=9;
			}
		}
	}
	private static void trans()
	{
		int[][]temp=new int[mux.length][mux.length];
		for(int i=0;i<mux.length;i++)
			for(int j=0;j<mux.length;j++)
				temp[i][j]=mux[j][i];
		mux=temp;
	}
	private static void print()
	{
		for(int i=0;i<mux.length;i++)
		{
			for(int j=0;j<mux.length;j++)
				out.append(mux[i][j]);
			out.append("\n");
		}
		out.append("\n");
	}
	public static void main (String[]args)throws Exception
	{
	BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
	int t=Integer.parseInt(stdin.readLine());
	for(int i=0;i<t;i++)
	{
		int n=Integer.parseInt(stdin.readLine());
		mux=new int[n][n];
		for(int j=0;j<n;j++)
		{
			String row=stdin.readLine();
			for(int k=0;k<n;k++)
				mux[j][k]=row.charAt(k)-'0';
		}

		int m=Integer.parseInt(stdin.readLine());
		for(int j=0;j<m;j++)
		{
			String op=stdin.readLine();
			if(op.charAt(0)=='t')
				trans();
			else if(op.charAt(0)=='d')
				dec();
			else if(op.charAt(0)=='i')
				inc();
			else {
				String[] ar=op.split(" ");
				int a=Integer.parseInt(ar[1])-1;
				int b=Integer.parseInt(ar[2])-1;
				if(ar[0].charAt(0)=='r')
					row(a,b);
				else
					col(a,b);
			}
		}
		out.append("Case #"+(i+1)+"\n");
		print();	
	}
	System.out.print(out);
	}
}
