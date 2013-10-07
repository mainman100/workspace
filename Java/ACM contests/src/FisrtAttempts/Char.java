package FisrtAttempts;
import java.io.*;
public class Char
{
	static char[]alpha={'A','B','C','D','E','F','G','H','I','J','K','L','M'
			,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public static boolean valid(char[][]sol,char c,int i,int j)
	{
		if(i!=0)
		{
			if(c==sol[i-1][j])
				return false;
		}
		if(i!=sol.length-1)
		{
			if(c==sol[i+1][j])
				return false;
		}
		if(j!=0)
		{
			if(c==sol[i][j-1])
				return false;
		}
		if(j!=sol.length-1)
		{
			if(c==sol[i][j+1])
				return false;
		}
		return true;
	}
	public static void fill(char[][]sol)
	{
		for(int i=0;i<sol.length;i++)
			for(int j=0;j<sol.length;j++)
			{
				int count=0;
				while(sol[i][j]=='.')
				{
					if(valid(sol,alpha[count],i,j)==true)
					{
						sol[i][j]=alpha[count];
						break;
					}
					count++;
				}
			}
	}
	public static void main(String[]args)throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		int numCases=Integer.parseInt(stdin.readLine());
				for(int i=1;i<=numCases;i++)
		{
			int n=Integer.parseInt(stdin.readLine());
			char[][] sol=new char[n][n];
			for(int j=0;j<n;j++)
			{
				String data=stdin.readLine();
				for(int w=0;w<n;w++)
				{
					sol[j][w]=data.charAt(w); 
				}
			}
			fill(sol);
			System.out.println("Case "+i+":");
			for(int g=0;g<n;g++)
			{
				for(int h=0;h<n;h++)
					System.out.print(sol[g][h]);
				System.out.println();
			}
			
		}
	}
}