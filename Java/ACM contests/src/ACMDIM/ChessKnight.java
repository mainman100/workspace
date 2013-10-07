package ACMDIM;

public class ChessKnight {

	double dp[][][]=new double[9][9][101];
	
	public double probability(int x, int y, int n)
	{
		if(out(x,y))
			return 0;
		if(dp[x][y][n]!=0)
			return dp[x][y][n];
		if(n==0)
			return 1;
		double prob=0;
		for(int di=-2;di<=2;di++)
			for(int dj=-2;dj<=2;dj++)
			{
				if(Math.abs(di*dj)!=2)
					continue;
				prob+=probability(x+dj, y+di, n-1);
			}
		return dp[x][y][n]=prob/8;
	}
	boolean out(int x,int y)
	{
		return x>8||x<1||y>8||y<1;
	}
	public static void main(String[] args) {
		ChessKnight c=new ChessKnight();
		System.out.println(c.probability(4, 3,50));
	}
}
