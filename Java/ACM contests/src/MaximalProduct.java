import java.util.Arrays;


public class MaximalProduct {

	long [][]dp=new long[101][21];
	public MaximalProduct()
	{
		for(int i=0;i<101;i++)
			Arrays.fill(dp[i], -1);
	}
	public 	long maximalProduct(int s, int k)
	{
		if(s<=0)
			return 0;
		if(k==1)
			return s;
		if(dp[s][k]!=-1)
			return dp[s][k];
		long max=0;
		for(int i=1;i<s;i++)
			max=Math.max(max,i*maximalProduct(s-i, k-1));
		return dp[s][k]=max;
	}
	public static void main(String[] args) {
		MaximalProduct m=new MaximalProduct();
		System.out.println(m.maximalProduct(100,20));
	}
}
