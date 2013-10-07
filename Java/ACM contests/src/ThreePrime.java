import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class ThreePrime {

	static boolean isPrime[]=new boolean[1000];
	static long[][]a=new long[100][2];
	static int MOD=10*10*10*10*10*10*10*10*10+9;
	public static void init()
	{
		Arrays.fill(isPrime, true);
		for(int i=2;i*i<=999;i++)
			if(isPrime[i])
			{
				for(int j=i;j*i<=999;j++)
					isPrime[i*j]=false;
			}
		
		for(int i=100;i<=999;i++)
			if(isPrime[i])
				a[i%100][0]++;
	}
	public static long dp(int n)
	{
		int[]pr={1,3,5,7,9};
		for(int i=1;i<=n-3;i++)
		{
			for(int k=0;k<100;k++)
				a[k][i%2]=0;
			for(int j=10;j<100;j++)
				for(int k:pr)
				{
					int num=10*j+k;
					if(isPrime[num])
						a[num%100][i%2]=(a[num%100][i%2]+a[j][(i-1)%2]%MOD)%MOD;
				}
		}
		long res=0;
		for(int i=0;i<100;i++)
			res=(res+a[i][(n-3)%2])%MOD;
		return res;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(st.readLine());
		init();
		System.out.println(dp(n));
	}
}
