import java.util.Arrays;
import java.util.StringTokenizer;


public class VolumeDiscount {

	int[]dp=new int[100];
	int[][]a;
	public int bestDeal(String[] priceList, int quantity)
	{
		int n=priceList.length;
		a=new int[n][2];
		StringTokenizer tok;
		for(int i=0;i<n;i++)
		{
			tok=new StringTokenizer(priceList[i]);
			a[i][0]=Integer.parseInt(tok.nextToken());
			a[i][1]=Integer.parseInt(tok.nextToken());
		}
		Arrays.fill(dp,-1);
		return f(quantity);
	}
	public int f(int w)
	{
		if(w<=0)
			return 0;
		if(dp[w]!=-1)
			return dp[w];
		int min=Integer.MAX_VALUE;
		for(int i=0;i<a.length;i++)
			min=Math.min(min, a[i][1]+f(w-a[i][0]));
		return dp[w]=min;
	}
}
