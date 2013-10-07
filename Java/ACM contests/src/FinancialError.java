import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class FinancialError {

	static int n;
	static int[]numbers;
	static StreamTokenizer str;
	static int[]sum;
	static int[]sumt;
	static int m;
	private static int r() throws IOException
	{
		str.nextToken();
		return (int)str.nval;
	}
	public static void main(String[] args) throws IOException {
		str=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		n=r();
		numbers=new int[n];
		long tempSum=0;
		for(int i=0;i<n;i++)
		{
			numbers[i]=r();
			tempSum+=numbers[i];
		}
		long sum=r();
		if(sum==tempSum)
		{
			System.out.println("Input has no error.");
			return;
		}
		solve();
	}
	public static void solve()
	{
		
	}
}
