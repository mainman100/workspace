package BUECamp;


import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class FootBall {

	static int teams;
	static int n;
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			n=Integer.parseInt(st.readLine());
			if(n==-1)
				break;
			teams=1<<n;
			double[][] pr=new double[teams][teams];
			for(int i=0;i<teams;i++)
			{
				String[] data=st.readLine().split(" ");
				for(int j=0;j<teams;j++)
					pr[i][j]=Double.parseDouble(data[j]);
			}
			System.out.println(solve2(pr));
		}
	}
	public static int solve(double[][] prob)
	{
		double[][]board=new double[n][teams];
		HashSet<Point> me=new HashSet<Point>();
		for(int i=0;i<teams;i+=2)
		{
			board[0][i]=prob[i][i+1];
			board[0][i+1]=prob[i+1][i];
			me.add(new Point(i,i+1));
			me.add(new Point(i+1,i));
		}
		int size=4;
		int group=teams/size;
		for(int round=1;round<n;round++)
		{
			for(int g=0;g<group;g++)
			{
				int[]now=new int[size];
				int startTeam=g*size;
				for(int i=0;i<size;i++)
					now[i]=startTeam++;
				for(int i=0;i<size;i++)
				{
					int team1=now[i];
					for(int j=0;j<size;j++)
					{
						if(i==j)
							continue;
						int team2=now[j];
						Point pair=new Point(team1,team2);
						if(me.contains(pair))
							continue;
						board[round][team1]+=board[round-1][team1]*board[round-1][team2]*prob[team1][team2];
						me.add(new Point(team1,team2));
					}
				}

			}
			group/=2;
			size*=2;
		}

		int team=0;
		for(int i=0;i<teams;i++)
			if(board[n-1][i]>board[n-1][team])
				team=i;
		return team+1;
	}
	public static int solve2(double[][]a)
	{
		double[][]board=new double[teams][n+1];
		for(int i=0;i<teams;i++)
			board[i][0]=1;
	    int b =0;
	    for (int r=0; r<n; r++) {
		b = 0 ;
		for (int i=0; i<teams; i++) {
		    double ss = 0 ;
		    for (int t=1<<r; t<(2<<r); t++)
			ss += board[t ^ i][r] * a[i][t ^ i] ;
		    board[i][r+1] = board[i][r] * ss ;
		    if (board[i][r+1] > board[b][r+1])
			b = i ;
		}
	    }
	    return b+1;
	}
	public static int solve3(double[][] prob)
	{
		double[][]board=new double[n][teams];
		for(int i=0;i<teams;i+=2)
		{
			board[0][i]=prob[i][i+1];
			board[0][i+1]=prob[i+1][i];
		}
		int size=4;
		int group=teams/size;
		for(int round=1;round<n;round++)
		{
			for(int g=0;g<group;g++)
			{
				int[]now=new int[size];
				int startTeam=g*size;
				for(int i=0;i<size;i++)
					now[i]=startTeam++;
				for(int i=0;i<size;i++)
				{
					int start=0,end=0;
					int team1=now[i];
					if(i<size/2)
					{
						start=size/2;
						end=size-1;
					}
					else
					{
						start=0;
						end=size/2-1;
					}
					for(int j=start;j<=end;j++)
					{
						int team2=now[j];
						board[round][team1]+=board[round-1][team1]*board[round-1][team2]*prob[team1][team2];
					}
				}

			}
			group/=2;
			size*=2;
		}

		int team=0;
		for(int i=0;i<teams;i++)
			if(board[n-1][i]>board[n-1][team])
				team=i;
		return team+1;
	}
}