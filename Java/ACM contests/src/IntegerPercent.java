import java.io.IOException;
import java.util.Scanner;


public class IntegerPercent {

	static int[]dp;
	static int s;
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		s=sc.nextInt();
		dp=new int[n+1];
		if(n<s)
			System.out.println(0);
		else
		{
			dp[s]=1;
			int max=1;
			for(int i=s+1;i<=n;i++)
			{
				for(int j=s;j<i;j++)
				{
					int div=((i-j)*100)/j;
					int rem=((i-j)*100)-j*div;
					if(rem==0&&div>=1&&div<=100)
						dp[i]=Math.max(dp[i], dp[j]);
				}
				if(dp[i]!=0)
					dp[i]+=1;
				max=Math.max(max, dp[i]);
			}
			System.out.println(max);
		}
	}
}
