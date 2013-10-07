import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Slalom {

	static int n;
	static int[][] paths;
	static pair[] points;
	static pair[][]tree;
	static int size=1<<17;
	static StringBuffer out = new StringBuffer();

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(st.readLine());
		tree=new pair[2][2*size];
		for(int j=0;j<=1;j++)
		for(int i=0;i<2*size;i++)
			tree[j][i]=new pair(0,0);
		StringTokenizer tok;
		paths = new int[2][n];
		points = new pair[n];
		Arrays.fill(paths[0], -1);
		Arrays.fill(paths[1], -1);
		for (int i = 0; i < n; i++) {
			tok = new StringTokenizer(st.readLine());
			int x = Integer.parseInt(tok.nextToken());
			int y = Integer.parseInt(tok.nextToken());
			points[i] = new pair(x, y, i + 1);
		}
		Arrays.sort(points);
		System.out.println(solve());
		System.out.println(out.substring(0,out.length()-1));
	}

	public static void print(int j, int i) {
		if (i == -1)
			return;
		out.append(points[i].index + " ");
		print(1-j,paths[j][i]);
	}
	public static void insert(int index,int right,int val,int pos)
	{
		index+=size;
		tree[right][index].y=val;
		tree[right][index].index=pos;
		index>>=1;
		while(index>0)
		{
			if(tree[right][index].y<val)
			{
				tree[right][index].y=val;
				tree[right][index].index=pos;
				
			}
			index>>=1;
		}
	}
	public static int solve()
	{
		int max=0;
		int maxPos=0;
		int maxRight=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<=1;j++)
			{
				pair p=points[i];
				pair best=null;
				if(j==1)	
					best=query(1,0,p.x+1,99999,0,size-1);
				else		
					best=query(1,1,0,p.x-1,0,size-1);
				insert(p.x,j,best.y+1,i);
				paths[j][i]=best.index;
				if(max<best.y+1)
				{
					maxPos=i;
					maxRight=j;
					max=best.y+1;
				}
			}
		print(maxRight,maxPos);
		return max;
	}
	public static pair query(int index,int right,int i,int j,int b,int e)
	{
		if(i>e||j<b)
			return new pair(-1,-1);
		if(i<=b&&j>=e)
			return tree[right][index];
		int mid=(b+e)/2;
		pair qLeft=query(2*index, right, i, j, b, mid);
		pair qRight=query(2*index+1, right, i, j, mid+1, e);
		if(qLeft.y<qRight.y)
			return qRight;
		return qLeft;
	}
	static class pair implements Comparable<pair> {
		int x, y, index=-1;

		public pair(int x, int y, int index) {
			super();
			this.x = x;
			this.y = y;
			this.index = index;
		}
		public pair(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(pair e) {
			return y-e.y;
		}
		public String toString()
		{
			return x+" , "+y+" , "+index;
		}
	}

}
