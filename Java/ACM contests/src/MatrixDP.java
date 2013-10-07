import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class MatrixDP {
	


	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int n=readInt();
		node[][] a=new node[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				int x=readInt();
				int temp=x;
				int count2=0;
				while(true)
				{
					if(temp%2!=0)
						break;
					temp/=2;
					count2++;
				}
				temp=x;
				int count5=0;
				while(true)
				{
					if(temp%5!=0)
						break;
					temp/=5;
					count5++;
				}
				int min=Math.min(count2,count5);
				a[i][j]=new node(min,count2-min,count5-min);
			}
		System.out.println(solve(a));
		String res="";
		int i=n-1;
		int j=n-1;
		while(true)
		{
			if(i==0&&j==0)
				break;
			res=a[i][j].path+res;
			int i1=i,j1=j;
			i-=a[i1][j1].du;
			j-=a[i1][j1].dl;
		}
		System.out.println(res);
	}
	public static int solve(node[][] a)
	{
		int n=a.length;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				if(i==0&&j==0)
					continue;
				int c2=a[i][j].twos;
				int c5=a[i][j].fives;
				int pairs=a[i][j].pairs;
				int uc2=-1,uc5=-1,upairs=-1,newUpairs=-1;
				int lc2=-1,lc5=-1,lpairs=-1,newLpairs=-1;
				int newPairs;
				if(i>0)
				{
					uc2=a[i-1][j].twos+c2;
					uc5=a[i-1][j].fives+c5;
					upairs=a[i-1][j].pairs+pairs;	
					newUpairs=Math.min(uc2, uc5);
					uc2-=newUpairs;
					uc5-=newUpairs;
				}
				if(j>0)
				{
					lc2=a[i][j-1].twos+c2;
					lc5=a[i][j-1].fives+c5;
					lpairs=a[i][j-1].pairs+pairs;
					newLpairs=Math.min(lc2,lc5);
					lc2-=newLpairs;
					lc5-=newLpairs;
				}
				if(i==0)
				{
					newPairs=lpairs+newLpairs;
					a[i][j].twos=lc2;
					a[i][j].fives=lc5;
					a[i][j].du=0;
					a[i][j].dl=1;
					a[i][j].path="R";
				}
				else if(j==0)
				{
					newPairs=upairs+newUpairs;
					a[i][j].twos=uc2;
					a[i][j].fives=uc5;
					a[i][j].du=1;
					a[i][j].dl=0;
					a[i][j].path="D";
				}
				else
				{
					newPairs=Math.min(lpairs+newLpairs,upairs+newUpairs);
					a[i][j].pairs=newPairs;
					if(newPairs==(lpairs+newLpairs))
					{
						a[i][j].twos=lc2;
						a[i][j].fives=lc5;
						a[i][j].du=0;
						a[i][j].dl=1;
						a[i][j].path="R";
					}
					else
					{
						a[i][j].twos=uc2;
						a[i][j].fives=uc5;
						a[i][j].du=1;
						a[i][j].dl=0;
						a[i][j].path="D";
					}
				}
			}
		return a[n-1][n-1].pairs;
	}
	
	static class node {
		int pairs;
		int twos;
		int fives;
		int du,dl;
		String path;
		public node(int pairs, int twos, int fives) {
			super();
			this.pairs = pairs;
			this.twos = twos;
			this.fives = fives;
		}
	}

}
