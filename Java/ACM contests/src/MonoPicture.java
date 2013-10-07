import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class MonoPicture {

	static int size=1<<20;
	static pair[] tree=new pair[1<<21];
	static pair dum = new pair(-1, -1);
	static int path[];
	static int MAX=1000000;
	public static void main(String[] args) throws IOException {
		StreamTokenizer st=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		st.nextToken();
		int n=(int)st.nval;
		path=new int[n];
		int[]a=new int[n];
		for(int i=0;i<n;i++)
		{
			st.nextToken();
			a[i]=(int)st.nval;	
		}
		for(int i=0;i<tree.length;i++)
			tree[i]=new pair(-1,0);
		Arrays.fill(path, -1);
		int max=0;
		int maxPos=0;
		for(int i=a.length-1;i>=0;i--)
		{
			pair left=dum,right=dum,middle;
			if(a[i]-2>=0)
				left=query(1, 0, a[i]-2, 0, size-1);
			if(a[i]+2<=MAX)
				right=query(1, a[i]+2, MAX, 0, size-1);
			middle=tree[a[i]+size];
			pair maxP=middle;
			if(maxP.y<left.y)
				maxP=left;
			if(maxP.y<right.y)
				maxP=right;
			path[i]=maxP.x;
			if(max<maxP.y+1)
			{
				max=maxP.y+1;
				maxPos=i;
			}
			insert(a[i], maxP.y+1, i);
		}
		System.out.println(n-max);
		StringBuffer out=new StringBuffer();
		int count=0;
		for(int i=maxPos;i>-1;i=path[i])
		{
			out.append(a[i]+" ");
			count++;
			if(count%1000==0)
			{
				System.out.print(out);
				out=new StringBuffer();
			}
		}
			System.out.println(out);
	}
	public static void insert(int index, int val, int pos) {
		index += size;
		tree[index].y = val;
		tree[index].x = pos;
		index >>= 1;
		while (index > 0) {
			if (tree[index].y < val) {
				tree[index].y = val;
				tree[index].x = pos;
			}
			index>>=1;
		}
	}

	public static pair query(int index, int i, int j, int b, int e) {
		if (i > e || j < b)
			return dum;
		if (i <= b && j >= e)
			return tree[index];
		int mid = (b + e) / 2;
		pair qLeft = query(index << 1, i, j, b, mid);
		pair qRight = query((index << 1) + 1, i, j, mid + 1, e);
		if (qLeft.y > qRight.y)
			return qLeft;
		return qRight;
	}
	static class pair
	{
		int x, y;

		public pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
