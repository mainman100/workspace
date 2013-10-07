package FisrtAttempts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class stones
{
	public static String collect(int[][]st)
	{
		int h=st.length;
		int w=st[0].length;
		int[][]max=new int [h][w];
		for(int i=0;i<w;i++)
			max[0][i]=st[0][i];
		for(int i=1;i<h;i++)
			for(int j=0;j<w;j++)
				max[i][j]=-1;
		for(int i=0;i<h-1;i++)
			for(int j=0;j<w;j++)
			{
				if(max[i][j]+st[i+1][j]>max[i+1][j])
					max[i+1][j]=max[i][j]+st[i+1][j];
				if(j!=0&&(max[i][j]+st[i+1][j-1]>max[i+1][j-1]))
					max[i+1][j-1]=max[i][j]+st[i+1][j-1];
				if(j!=w-1&&(max[i][j]+st[i+1][j+1]>max[i+1][j+1]))
					max[i+1][j+1]=max[i][j]+st[i+1][j+1];		
			}
		int maxStones=max[h-1][0];
		for(int j=1;j<w;j++)
			if(max[h-1][j]>maxStones)
				maxStones=max[h-1][j];
		return maxStones+"\n";
	}
	public static void main(String[]agrs)throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(stdin.readLine());
		int k=Integer.parseInt(st.nextToken());
		int[][]diagon;
		int M,N;
		StringBuffer out=new StringBuffer("");
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
			for(int i=0;i<M;i++)
			{
				for(int j=0;j<N;j++)
				{
					try
					{
						diagon[i][j]=Integer.parseInt(st.nextToken());						
					}
					catch(Exception e)
					{
						st=new StringTokenizer(stdin.readLine());
						diagon[i][j]=Integer.parseInt(st.nextToken());						
					}
				}	
			}
			out.append(collect(diagon));
		}
		System.out.println(out);
	}	
}
