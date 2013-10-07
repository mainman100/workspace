package FisrtAttempts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Modify
{
	public static int collect(int[][]st)
	{
		int[]now,next,val;
		int h=st.length;
		int w=st[0].length;
		int[][]max=new int [h][w];
		now=max[0];
		val=st[0];
		for(int i=0;i<w;i++)
			now[i]=val[i];
		for(int i=0;i<h-1;i++)
		{
			now=max[i];
			next=max[i+1];
			val=st[i+1];
			for(int j=0;j<w;j++)
			{
				if(now[j]+val[j]>next[j])
					next[j]=now[j]+val[j];
				if(j!=0&&(now[j]+val[j-1]>next[j-1]))
					next[j-1]=now[j]+val[j-1];
				if(j!=w-1&&(now[j]+val[j+1]>next[j+1]))
					next[j+1]=now[j]+val[j+1];		
			}	
		}
		int maxStones=max[h-1][0];
		now=max[h-1];
		for(int j=1;j<w;j++)
			if(now[j]>maxStones)
				maxStones=now[j];
		return maxStones;
	}
	public static void main(String[]agrs)throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(stdin.readLine());
		int k=Integer.parseInt(st.nextToken());
		int[][]diagon;
		int M,N;
		StringBuilder out=new StringBuilder("");
		st=new StringTokenizer(stdin.readLine());
		for(int count=0;count<k;count++)
		{
			if(!st.hasMoreTokens())
				st=new StringTokenizer(stdin.readLine());
			M=Integer.parseInt(st.nextToken());
			if(!st.hasMoreTokens())
				st=new StringTokenizer(stdin.readLine());
			N=Integer.parseInt(st.nextToken());
			diagon=new int[M][N];
			if(!st.hasMoreTokens())
				st=new StringTokenizer(stdin.readLine());
			int[]temp;
			for(int i=0;i<M;i++)
			{
				temp=diagon[i];
				for(int j=0;j<N;j++)
				{
					try
					{
						temp[j]=Integer.parseInt(st.nextToken());						
					}
					catch(Exception e)
					{
						st=new StringTokenizer(stdin.readLine());
						temp[j]=Integer.parseInt(st.nextToken());						
					}
				}	
			}
			//--------------
			
			//--------------
			out.append(collect(diagon));
			out.append("\n");
		}
		System.out.println(out);
	}	
}
