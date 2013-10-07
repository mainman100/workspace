package FisrtAttempts;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class rescue
{
	public static void rescueHarry(int[][]diagon,int a,int b,int T)
	{
		int m=diagon.length;
		int n=diagon[0].length;
		int[][]min=new int[m][n];
		boolean change=true;
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				min[i][j]=T+1;
		if(diagon[0][0]<=T)
			min[0][0]=diagon[0][0];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
			{
				if(min[i][j]<=T)
				{
					if(i!=0&&min[i][j]+diagon[i-1][j]<=min[i-1][j])
						min[i-1][j]=min[i][j]+diagon[i-1][j];				
					if(i!=m-1&&min[i][j]+diagon[i+1][j]<=min[i+1][j])
						min[i+1][j]=min[i][j]+diagon[i+1][j];
					if(j!=0&&min[i][j]+diagon[i][j-1]<=min[i][j-1])
						min[i][j-1]=min[i][j]+diagon[i][j-1];
					if(j!=n-1&&min[i][j]+diagon[i][j+1]<=min[i][j+1])
						min[i][j+1]=min[i][j]+diagon[i][j+1];
				}
			}
		int count=1;
		while(change==true)
		{
			count++;
			System.out.println(count);
			change=false;
			for(int i=0;i<m;i++)
				for(int j=0;j<n;j++)
				{
					if(min[i][j]<=T)
					{
						if(i!=0&&min[i][j]+diagon[i-1][j]<=min[i-1][j])
						{
							min[i-1][j]=min[i][j]+diagon[i-1][j];				
							change=true;
						}
						if(i!=m-1&&min[i][j]+diagon[i+1][j]<=min[i+1][j])
						{
							min[i+1][j]=min[i][j]+diagon[i+1][j];
							change=true;
						}
						if(j!=0&&min[i][j]+diagon[i][j-1]<=min[i][j-1])
						{
							min[i][j-1]=min[i][j]+diagon[i][j-1];
							change=true;
						}
						if(j!=n-1&&min[i][j]+diagon[i][j+1]<=min[i][j+1])
						{
							min[i][j+1]=min[i][j]+diagon[i][j+1];
							change=true;
						}
					}
				}
		}
		System.out.println(count);
		if(min[a-1][b-1]<=T)
		{
			System.out.println("YES");
			System.out.println(T-min[a-1][b-1]);
		}
		else
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
			rescueHarry(diagon,a,b,T);
		}
	}
}