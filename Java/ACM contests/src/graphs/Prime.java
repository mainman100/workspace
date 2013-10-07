package graphs;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Prime 
{
	static StreamTokenizer st;
	static boolean isPrime[]=new boolean[10000];
	private static void initPrime()
	{
		Arrays.fill(isPrime,true);
		isPrime[0]=isPrime[1]=false;
		for(int i=2;i<10000;i++)
			for(int j=2;j<i&&isPrime[i];j++)
				if(i%j==0)
					isPrime[i]=false;
	}
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main(String[]args) throws Exception
	{
		initPrime();
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		int t=readInt();
		for(int i=0;i<t;i++)
		{
			Queue<Integer> q=new LinkedList<Integer>();
			int[]cost=new int[10000];
			Arrays.fill(cost,-1);
			int a=readInt();
			int b=readInt();
			cost[a]=0;
			q.add(a);
			while(true)
			{
				if(q.isEmpty())
				{
					out.append("Impossible\n");
					break;
				}
				int x=q.poll();
				if(x==b)
				{
					out.append(cost[b]+"\n");
					break;
				}
				int temp=x;
				int[]digits=new int[4];
				for(int l=0;l<4;l++)
				{
					digits[l]=temp%10;
					temp/=10;
				}
				for(int l=0,mult=1;l<4;l++,mult*=10)//for each digit
					for(int j=(l<3?0:1);j<10;j++)
					{
						temp=x+(j-digits[l])*mult;
						if(temp!=x&&isPrime[temp])
						{
							if(cost[temp]==-1)
							{
								cost[temp]=cost[x]+1;
								q.add(temp);
							}
						}
					}
			}
		}
		System.out.print(out);
	}
}
