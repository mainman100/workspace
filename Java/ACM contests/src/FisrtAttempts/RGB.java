package FisrtAttempts;
import java.util.Scanner;
public class RGB
{
	public static int min(int x,int y)
	{
		if(x<y)
			return x;
		return y;
	}
	public static int min(int x,int y,int z)
	{
		return min(z,min(x,y));
	}
	public static char color(int[][]cost,int i,char prev)
	{
		int min;
		int red=cost[0][i];
		int green=cost[1][i];
		int blue=cost[2][i];
		if(prev=='R')
		{
			min=min(green,blue);
			if(min==green)
				return 'G';
			return 'B';
		}
		if(prev=='G')
		{
			min=min(red,blue);
			if(min==red)
				return 'R';
			return 'B';
		}
		if(prev=='B')
		{
			min=min(green,red);
			if(min==green)
				return 'G';
			return 'R';
		}
		return ' ';
	}
	public static String path(int[][]cost)
	{
		int l=cost[0].length;
		int red=cost[0][l-1];
		int green=cost[1][l-1];
		int blue=cost[2][l-1];
		char prev;
		int min=red;
		prev='R';
		if(blue<min)
		{
			blue=min;
			prev='B';
		}
		if(green<min)
		{
			min=green;
			prev='G';
		}
		String s=prev+"";
		for(int i=l-2;i>=0;i--)
		{
			s=color(cost,i,s.charAt(0))+s;
		}
		return s;
	}
	public static int Cost(String[]s)
	{
		int[][]colors=new int[3][s.length];
		Scanner read=new Scanner(s[0]);
		int red=read.nextInt();
		int green=read.nextInt();
		int blue=read.nextInt();
		colors[0][0]=red;;
		colors[1][0]=green;
		colors[2][0]=blue;
		int i=1;
		for(i=1;i<s.length;i++)
		{
			read=new Scanner(s[i]);
			red=read.nextInt();
			green=read.nextInt();
			blue=read.nextInt();
			colors[0][i]=red+min(colors[1][i-1],colors[2][i-1]);
			colors[1][i]=green+min(colors[0][i-1],colors[2][i-1]);
			colors[2][i]=blue+min(colors[0][i-1],colors[1][i-1]);
		}
		i--;
		System.out.println(path(colors));
		return min(colors[0][i],colors[1][i],colors[2][i]);
	}
	public static void main(String[]args)
	{
		String[] s={"1 100 100", "100 100 100", "1 100 100"};
		System.out.println(Cost(s));
	}
}