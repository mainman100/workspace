package FisrtAttempts;
public class Matrix
{
	public static int[][] matrix(int[]dim)
	{	
		int n=dim.length-1;
		int pos[][]=new int[n][n];
		int[][] opt=new int[n][n];
		boolean[][]used=new boolean[n][n];
		for(int i=0;i<n;i++)
			opt[i][i]=0;
		int j=0;
		for(int l=2;l<=n;l++)
			for(int i=l;i<n-l+1;i++)
			{
				j=i+l-1;
				int res=0;
				for(int k=i;k<j;k++)
				{
					res=opt[i][k]+opt[k+1][j]+dim[i-1]*dim[k]*dim[j];
					if(used[i][j]==false)
						opt[i][j]=res;
					if(res<opt[i][j])
					{
						opt[i][j]=res;
						pos[i][j]=k;
					}
				}	
			}
		return pos;
	}
}
