import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class MusicalChairs {

	static int size=1;
	static int []tree;
	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			StringTokenizer tok=new StringTokenizer(st.readLine());
			int n=Integer.parseInt(tok.nextToken());
			int d=Integer.parseInt(tok.nextToken());
			if(n==0&&d==0)
				break;
			size=1;
			while(size<n)
				size<<=1;
			if(size==n)
				size<<=1;
			tree=new int[size<<1];
			for(int i=1;i<=n;i++)
				insert(i,1);
			int get=0;
			for(int i=n;i>1;i--)
			{
				get=(get+d)%i;
				if(get==0)
					get=i;
				int pos=access(get);
				insert(pos,-1);
				get--;
			}
			System.out.println(n+" "+d+" "+access(1));
		}
	}
	public static void insert(int index,int val)
	{
		index+=size;
		tree[index]+=val;
		index>>=1;
		while(index>0)
		{
			tree[index]+=val;
			index>>=1;
		}
	}
	public static void remove(int index)
	{
		index+=size;
		tree[index]=0;
		index>>=1;
		while(index>0)
		{
			tree[index]--;
			index>>=1;
		}
	}
	public static int access(int k)
	{
		int start=1;
		while(start<size)
		{
			start<<=1;
			if(tree[start]<k)
			{
				k-=tree[start];
				start++;
			}
		}
		return start-size;
	}
}
