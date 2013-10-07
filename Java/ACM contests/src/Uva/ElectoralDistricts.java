package Uva;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;


public class ElectoralDistricts {

	static StreamTokenizer st;
	static int n;
	static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main (String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=r();
		while(t-->0)
		{
			n=r();
			int[][]a=new int[n][n];
			int[][]b=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					a[i][j]=r();
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
					b[i][j]=r();
			System.out.println(max(a,b));
		}
	}
	public static int max(int[][]a,int[][]b)
	{
		int hor=0;
		for(int i=0;i<n;i++)
		{
			int as=0,bs=0;
			for(int j=0;j<n;j++)
			{
				as+=a[i][j];
				bs+=b[i][j];
			}
			if(as>bs)
				hor++;
			else if(as<bs)
				hor--;
		}
		int ver=0;
		for(int i=0;i<n;i++)
		{
			int as=0,bs=0;
			for(int j=0;j<n;j++)
			{
				as+=a[j][i];
				bs+=b[j][i];
			}
			if(as>bs)
				ver++;
			else if(as<bs)
				ver--;
		}
		return Math.max(hor,ver);
	}
}
