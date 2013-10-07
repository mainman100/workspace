package other;

import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class PrimeGenerator
{


	static StreamTokenizer st;
	static boolean[] isPrime;
	private static long readInt()throws Exception
	{
 	   st.nextToken();
 	   return  (long)st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		int t=(int)readInt();
		for(int i=0;i<t;i++)
		{
			long m=readInt();
			long n=readInt();
			int j=0;
			for(j=0;j<isPrime.length;j++)
				if(j>=m)
					break;
			for(;j<isPrime.length;j++)
			{
				if(j>n)
					break;
				out.append(isPrime[j]+"\n");
			}
		}
		System.out.print(out);
	}
	public static void segment_Seive(int m,int n)
	{
		
	}
	public static boolean[] seive(long n)
	{
		
		Arrays.fill(isPrime,true);
	//	LinkedList<Integer> temp=new LinkedList<Integer>();
        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]) {
      //      	temp.addLast(i);
                for (int j = i; i*j <= n; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        return isPrime;
	}
}
