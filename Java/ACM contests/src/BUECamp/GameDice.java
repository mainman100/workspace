package BUECamp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameDice {

	static Long[][]dp;
	static int[]d;
	static int n;
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String data=st.readLine();
			if(data.length()==1)
				break;
			String[]da=data.split(" ");
			d=new int[da.length-2];
			for(int i=0;i<d.length;i++)
				d[i]=Integer.parseInt(da[i+1].substring(1));
			int roll=Integer.parseInt(da[da.length-1]);
			n=d.length;
			dp=new Long[n][roll+1];
			long poss=1;
			for(int i=0;i<d.length;i++)
				poss*=d[i];
			long valid=solve(0,roll);
			double res=valid*1.0/poss;
			System.out.printf("%.5f\n",res);
		}
	}
	public static long solve(int i,int roll)
	{
		if(i==n&&roll==0)
			return 1;
		if(roll<0)
			return 0;
		if(i>=n)
			return 0;

		if(dp[i][roll]!=null)
			return dp[i][roll];
		long sum=0;
		for(int j=1;j<=d[i];j++)
			sum+=solve(i+1,roll-j);
		return dp[i][roll]=sum;
	}
}
