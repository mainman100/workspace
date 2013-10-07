package ACMDIM;

import java.util.Arrays;
import java.util.HashSet;

public class Queen {

	int solutions=0;
	int[]x;
	boolean[]y,d1,d2;
	int N;
	HashSet<form> forms;
	public Queen(int n)
	{
		N=n;
		x=new int[N];
		y=new boolean[N];
		d1=new boolean[2*N-1];
		d2=new boolean[2*N-1];
		Arrays.fill(x,-1);
		forms=new HashSet<form>();
	}
	public void rec(int i)
	{
		if(i==N)
		{
			++solutions;
			forms.add(new form(x));
			return;
		}
		for(int j=0;j<N;j++)
		{
			if(canPlace(i, j))
			{
				place(i,j,true);
				rec(i+1);
				place(i,j,false);
			}
		}
	}
	public boolean canPlace(int i,int j)
	{
		return !y[j]&&!d1[i+j]&&!d2[N-1+i-j];
	}
	public void place(int i,int j,boolean place)
	{
		y[j]=d1[i+j]=d2[N-1+i-j]=place;
		x[i]=place?j:-1;
	}
	public int removeDups()
	{
		HashSet<form> dups=new HashSet<form>();
		form[]arr=forms.toArray(new form[0]);
		for(form fo:arr)
		{
			if(dups.contains(fo))
				continue;
			dups.add(fo);
			int[]x=Arrays.copyOf(fo.x,fo.x.length);
			//First 3 rotations
			for(int c=0;c<3;c++)
			{
				int[]y=new int[N];
				for(int i = 0 ; i < N ; i++)
					y[N-1-x[i]] = i ;	
				form rem = new form(y) ;
				if(!rem.equals(fo))
					forms.remove(rem) ;	
				dups.add(rem);
				x=y;
			}
			//reflection
			x=Arrays.copyOf(fo.x,fo.x.length);
			int[]y=new int[N];
			for(int i=0;i<N;i++)
				y[i]=N-1-x[i];
			form rem=new form(y);
			if(!rem.equals(fo))
				forms.remove(rem);
			dups.add(rem);
			x=y;
			//Second 3 rotation
			for(int c=0;c<3;c++)
			{
				y=new int[N];
				for(int i = 0 ; i < N ; i++)
					y[N-1-x[i]] = i ;
				
				rem = new form(y) ;
				if(!rem.equals(fo))
					forms.remove(rem) ;	
				dups.add(rem);
				x=y;
			}
		}
		return forms.size();
	}
	public static void main(String[]args)
	{
		Queen q=new Queen(8);
		q.rec(0);
		System.out.println(q.removeDups());
	}
	static class form
	{
		int[]x;
		public form(int[]y)
		{
			x=Arrays.copyOf(y,y.length);
		}
		public boolean equals(Object e)
		{
			int[]y=((form)e).x;
			return Arrays.equals(x,y);
		}
		public int hashCode()
		{
			return Arrays.hashCode(x);
		}
		public String toString()
		{
			String res="...........................\n";
			for(int i=0;i<x.length;i++)
				res+=x[i]+" , ";
			return res+"\n...............";
		}
	}
}
