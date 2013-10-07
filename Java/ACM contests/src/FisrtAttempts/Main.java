package FisrtAttempts;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main
{
	public static void main(String[]agrs)throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(stdin.readLine());
		int k=Integer.parseInt(st.nextToken());
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
			if(!st.hasMoreTokens())
				st=new StringTokenizer(stdin.readLine());
			int[] nowMax=new int[N],nextMax=new int[N],val=null;
			for(int i=0;i<M;i++)
			{
				nowMax=nextMax;
				nextMax=new int[N];
				val=new int[N];
				for(int j=0;j<N;j++)
				{
					try
					{
						val[j]=Integer.parseInt(st.nextToken());						
					}
					catch(Exception e)
					{
						st=new StringTokenizer(stdin.readLine());
						val[j]=Integer.parseInt(st.nextToken());
					}	
				}
				for(int j=0;j<N;j++)
				{
					if(i!=0)
					{
						if(nowMax[j]+val[j]>nextMax[j])
							nextMax[j]=nowMax[j]+val[j];
						if(j!=0&&(nowMax[j]+val[j-1]>nextMax[j-1]))
							nextMax[j-1]=nowMax[j]+val[j-1];
						if(j!=N-1&&(nowMax[j]+val[j+1]>nextMax[j+1]))
							nextMax[j+1]=nowMax[j]+val[j+1];		
					}
					else
						nextMax[j]=val[j];
				}	
			}
			int max=nextMax[0];
			for(int h=1;h<N;h++)
				if(nextMax[h]>max)
					max=nextMax[h];
			out.append(max);
			out.append("\n");
		}
		System.out.println(out);
	}	
}
