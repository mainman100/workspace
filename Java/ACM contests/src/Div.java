import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class Div {


	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	private static int sum(int n)
	{
		int sum=0;
		while(n!=0)
		{
			sum+=n%10;
			n/=10;
		}
		return sum;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		StringBuffer out=new StringBuffer();
		int t=readInt();
		for(int i=0;i<t;i++)
		{
			int a=readInt();
			int b=readInt();
			int k=readInt();
			int r=a%k;
			int first=a;
			if(r!=0)
				first=a+k-r;
			int count=0;
			for(int j=first;j<=b;j=j+k)
			{
				if(sum(j)%k==0)
					count++;
			}
			out.append(count+"\n");
		}
		System.out.print(out);
	}
}
