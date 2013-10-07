package graphs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Mice 
{
	static StreamTokenizer st;
	private static int readInt() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int n=readInt();
		int exist=readInt()-1;
		int t=readInt();
		int con=readInt();
		int adj[][]=new int[n][n];
		for(int i=0;i<n;i++)
			Arrays.fill(adj[i],-1);
		for(int i=0;i<n;i++)
			adj[i][i]=0;
		for(int h=0;h<con;h++)
		{
			int a=readInt();
			int b=readInt();
			int c=readInt();
			adj[a-1][b-1]=c;
		}
		for(int k=0;k<n;k++)
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
				{
					if(adj[i][k]==-1||adj[k][j]==-1)
						continue;
					if(adj[i][j]==-1)
						adj[i][j]=adj[i][k]+adj[k][j];
					else
						adj[i][j]=Math.min(adj[i][j],adj[i][k]+adj[k][j]);
				}
		int count=0;
		for(int i=0;i<n;i++)
			if(adj[i][exist]<=t&&adj[i][exist]!=-1)
				count++;
		System.out.println(count);
	}
}
