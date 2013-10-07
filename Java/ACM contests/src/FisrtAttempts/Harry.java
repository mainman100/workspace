package FisrtAttempts;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Harry
{
	public static void rescue(int[][]diagon,int a,int b,int T)
	{
		int m=diagon.length;
		int n=diagon[0].length;
		boolean can[][][]=new boolean[m][n][T+1];
		int begin=diagon[0][0];
		if(begin<=T)
			can[0][0][begin]=true;
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				for(int k=0;k<=T;k++)
				{
					if(can[i][j][k]==true)//We can reach it within T seconds
					{
						if(i!=0&&k+diagon[i-1][j]<=T)
							can[i-1][j][k+diagon[i-1][j]]=true;				
						if(i!=m-1&&k+diagon[i+1][j]<=T)
							can[i+1][j][k+diagon[i+1][j]]=true;
						if(j!=n-1&&k+diagon[i][j+1]<=T)
							can[i][j+1][k+diagon[i][j+1]]=true;
						if(j!=0&&k+diagon[i][j-1]<=T)
							can[i][j-1][k+diagon[i][j-1]]=true;
					}
				}
		for(int i=0;i<=T;i++)
		{
			if(can[a-1][b-1][i]==true)
			{
				System.out.println("YES");
				System.out.println(T-i);
				return;
			}
		}
		System.out.println("NO");
	}
	public static void main(String[]args)throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		Scanner read=new Scanner(stdin.readLine());
		int k=read.nextInt();
		int[][]diagon;
		int M,N,a,b,T;
		for(int count=0;count<k;count++)
		{
			read=new Scanner(stdin.readLine());
			M=read.nextInt();
			N=read.nextInt();
			diagon=new int[M][N];
			for(int i=0;i<M;i++)
			{
				read=new Scanner(stdin.readLine());
				for(int j=0;j<N;j++)
				{
					diagon[i][j]=read.nextInt();
				}	
			}
			read=new Scanner(stdin.readLine());
			a=read.nextInt();
			b=read.nextInt();
			T=read.nextInt();
			rescue(diagon,a,b,T);
		}
	}
}