package DJ1907;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;

public class Software {

	static StreamTokenizer st;
	static boolean [][]adj;
	static int n,m;
	static LinkedList<Integer> res;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		while(true)
		{
			n=r();
			m=r();
			if(n==0&&m==0)
				break;
			adj=new boolean[n][n];
			for(int i=0;i<m;i++)
			{
				int t1=r();
				int t2=r();
				adj[t1][t2]=true;
				adj[t2][t1]=true;
			}
			res=new LinkedList<Integer>();
			solve();
			Integer[]a=res.toArray(new Integer[0]);
			boolean[]temp=new boolean[n];
			for(int i=0;i<a.length;i++)
				temp[a[i]]=true;
			String out="";
			for(int i=0;i<n;i++)
				if(temp[i])
					out+="1";
				else
					out+="0";
			System.out.println(out);
		}
	}
	public static void solve()
	{
		for(int i=n-1;i>=0;i--)
		{
			for(int j=0;j<n;j++)
				if(adj[i][j])
				{
					res.add(j);
					for(int k=0;k<n;k++)
					{
						adj[j][k]=false;
						adj[k][j]=false;
					}
					adj[i][j]=false;
					adj[j][i]=false;
				}
		}
	}
}
