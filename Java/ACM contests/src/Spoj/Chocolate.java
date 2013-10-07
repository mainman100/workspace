package Spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Chocolate {

	static int[]x;
	static int[]y;
	static StreamTokenizer st;
	public static void main (String[]args)throws Exception
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(st.readLine());
		st.readLine();
		while(t-->0)
		{
			String[]d=st.readLine().split(" ");
			int m=Integer.parseInt(d[0]);
			int n=Integer.parseInt(d[1]);
			x=new int[m+2];
			y=new int[n+2];
			x[0]=0;
			y[0]=0;
			for(int i=1;i<m;i++)
				x[i]=Integer.parseInt(st.readLine());
			x[x.length-1]=0;
			for(int i=1;i<n;i++)
				y[i]=Integer.parseInt(st.readLine());
			y[y.length-1]=0;
			System.out.println(f(0,0,m+1,n+1));
		}
	
	}
/*	public static int f(int x1,int y1,int x2,int y2)
	{
	//	System.out.println(x1+" "+y1+" "+x2+" "+y2);
		if(Math.abs(y1-y2)==1&&Math.abs(x1-x2)==1)
			return 0;
		int tmin=Math.min(x1,x2);
		int tmax=Math.max(x1,x2);
		int min=-1;
		for(int i=tmin+1;i<tmax;i++)
		{
			int right=f(i,y1,x2,y2);
			int left=f(x1,y1,i,y2);
			if(min==-1)
				min=right+left+x[i];
			else
				min=Math.min(min,x[i]+right+left);
		}
		tmin=Math.min(y1,y2);
		tmax=Math.max(y1,y2);
		for(int i=tmin+1;i<tmax;i++)
		{
			int up=f(x1,y1,x2,i);
			int down=f(x1,i,x2,y2);
			if(min==-1)
				min=up+down+y[i];
			else
				min=Math.min(min,y[i]+up+down);
		}
		return min;
	}*/
	public static int f(int x1,int y1,int x2,int y2)
	{
			System.out.println(x1+" "+y1+" "+x2+" "+y2);
		if(Math.abs(y1-y2)==1&&Math.abs(x1-x2)==1)
			return 0;
		int maxH=-1,maxV=-1;
		int posH=0,posV=0;
		for(int i=x1+1;i<x2;i++)
		{
			maxV=Math.max(maxV,x[i]);
			posV=i;
		}
		for(int i=y1+1;i<y2;i++)
		{
			maxH=Math.max(maxH,y[i]);
			posH=i;	
		}
		int resH=0,resV=0;
		if(maxV!=-1)
			resV=maxV+f(x1,y1,posV,y2)+f(posV,y1,x2,y2);
		if(maxH!=-1)
			resH= maxH+f(x1,y1,x2,posH)+f(x1,posH,x2,y2);
		if(maxH==-1)
			return resV;
		if(maxV==-1)
			return resH;
		return Math.min(resH,resV);
	}
}
