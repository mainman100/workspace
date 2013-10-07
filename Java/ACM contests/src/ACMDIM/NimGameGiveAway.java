package ACMDIM;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;

public class NimGameGiveAway {

	static StreamTokenizer st;
	public static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException {
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int numOnes=0;
		int numZeros=0;
		int sum=0;
		for(int i=0;i<4;i++)
		{
			int n=r();
			sum^=n;
			if(n==1)
				numOnes++;
			if(n==0)
				numZeros++;
		}
		if(numOnes+numZeros==4)
		{
			if(numOnes%2==1)
				System.out.println("Second wins.");
			else
				System.out.println("First wins.");
		}
		else
		{
			if(sum==0)
				System.out.println("Second wins.");
			else
				System.out.println("First wins.");
		}
		
	}
}
