import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class FrequentValues {

	static int[]tree=new int[1<<19];
	static int[]a=new int[1<<18];
	static StreamTokenizer st;
	static int n,q;
	static int offSet=100000;
	private static int r() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	static void construct(int node,int i,int j)
	{
		if(i==j)
		{
			tree[node]=a[i];
			return;
		}
		construct(2*node,i, (i+j)/2);
		construct(2*node+1,(i+j)/2+1,j);
		tree[node]=Math.max(tree[2*node],tree[2*node+1]);
	}
	static int query(int node,int i,int j,int b,int e)
	{
		System.out.println(b+" , "+e);
		if(i<=b&&j>=e)
			return tree[node];
		if(i>e||j<b)
			return -1;
		int mid=(b+e)/2;
		int left=query(2*node, i, j, b, mid);
		int right=query(2*node+1, mid+1, j, mid+1, e);
		return Math.max(left, right);
	}
	public static void main(String[] args) throws IOException {
		st=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		n=r();
		if(n==0)
			return;
		q=r();	
		int prev=0;
		for(int i=0;i<n;i++)
		{
			if(i==0)
			{
				a[0]=1;
				prev=r();
				continue;
			}
		}
		construct(1, 0, n-1);
		for(int k=0;k<1;k++)
			System.out.println(query(1, r()-1, r()-1, 0, n-1));
	}
}
