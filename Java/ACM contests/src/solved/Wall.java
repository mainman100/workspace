package solved;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class Wall 
{

	static StreamTokenizer st;
	static int[] dp;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	int n;
	while((n=readInt())!=0)
	{
		dp=new int[n+1];
		dp[1]=1;
		if(n>1)
			dp[2]=2;
		for(int i=3;i<=n;i++)
			dp[i]=dp[i-1]+dp[i-2];
		out.append(dp[n]+"\n");
	}
	System.out.print(out);
	}
}
