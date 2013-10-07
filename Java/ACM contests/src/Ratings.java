import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Ratings {

	static int[]tree;
	static int size=1<<17;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(st.readLine());
		tree=new int[1<<18];
		rate[]arr=new rate[n];
		rate[]temp=new rate[n];
		StringTokenizer tok;
		for(int i=0;i<n;i++)
		{
			tok=new StringTokenizer(st.readLine());
			int a=Integer.parseInt(tok.nextToken());
			int h=Integer.parseInt(tok.nextToken());
			arr[i]=new rate(h,a, i);
			temp[i]=new rate(a,h,i);
		}
		Arrays.sort(arr);
		Arrays.sort(temp);
	//	System.out.println(Arrays.toString(temp));
		int[]res=new int[n];
		for(int i=0;i<n;i++)
		{
			int sum=sum(1, 0, arr[i].open-1, 1, size-1);
			res[arr[i].index]=sum;
			insert(arr[i].open);
			int search=arr[i].open;
		//	System.out.println(search);
			int low=0;
			int high=n-1;
			if(temp[0].high==search)
			{
				high=0;
		//		System.out.println("in if one");
			}
			while(low+1<high)
			{
				int mid=(low+high)/2;
				if(temp[mid].high<search)
					low=mid;
				else
					high=mid;
			}
			int start=high;
			low=0;
			high=n-1;
			if(temp[n-1].high==search)
			{
				low=n-1;
		//		System.out.println("in if two");
			}
			while(low+1<high)
			{
				int mid=(low+high)/2;
				if(temp[mid].high<=search)
					low=mid;
				else
					high=mid;
			}
			int end=low;
		//	System.out.println(start+" "+end);
			search=arr[i].high;
			low=start;
			high=end;
			if(temp[start].open==search)
			{
				low=start-1;
				high=low;
			}
			while(low+1<high)
			{
				int mid=(low+high)/2;
				if(temp[i].open<search)
					low=mid;
				else
					high=mid;
			}
		//	System.out.println("here");
			res[arr[i].index]+=low-start+1;
		}
		for(int i=0;i<n;i++)
			System.out.println(res[i]);
	}
	static void insert(int index)
	{
		index+=size;
		tree[index]++;
		index>>=1;
		while(index>0)
		{
			tree[index]++;
			index>>=1;
		}
	}
	static int sum(int index,int i,int j,int b,int e)
	{
		if(i>e||j<b)
			return 0;
		if(i<=b&&j>=e)
			return tree[index];
		int mid=(b+e)/2;
		int ql=sum(2*index, i, j, b, mid);
		int qr=sum(2*index+1,i,j,mid+1,e);
		return ql+qr;
	}
	static class rate implements Comparable<rate>
	{
		int high,open,index;

		public rate(int high, int open,int index) {
			super();
			this.high = high;
			this.open = open;
			this.index=index;
		}

		@Override
		public int compareTo(rate e) {
			if(high!=e.high)
				return high-e.high;
			return open-e.open;
		}
		public String toString()
		{
			return index+" : "+high+" "+open;
		}
	}
}
