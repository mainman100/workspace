package ENC10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {

	static int size=1<<15;
	static int[]tree=new int[1<<16];
	static pair[]sold;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new FileReader("C:\\Users\\Loai_Ghoraba\\Desktop\\ENC10TestData\\A. Sir Yes Sir\\sir.in"));
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			int n=Integer.parseInt(st.readLine());
			sold=new pair[n];
			StringTokenizer tok=new StringTokenizer(st.readLine());
			for(int i=0;i<n;i++)
				sold[i]=new pair(i,Integer.parseInt(tok.nextToken()));
			Arrays.sort(sold);
			Arrays.fill(tree,0);
			int sum=0;
			for(int i=0;i<n;i++)
			{
				sum+=sum(1,sold[i].index+1,n-1,0,size-1);
				insert(sold[i].index);
			}
			System.out.println(sum);
		}
	}
	public static void insert(int index)
	{
		index+=size;
		tree[index]=1;
		index>>=1;
		while(index>0)
		{
			tree[index]++;
			index>>=1;
		}
	}
	public static int sum(int index,int i,int j,int b,int e)
	{
		if(i>e||j<b)
			return 0;
		if(i<=b&&j>=e)
			return tree[index];
		int ql=sum(2*index,i,j,b,(b+e)/2);
		int qr=sum(2*index+1,i,j,1+(b+e)/2,e);
		return ql+qr;
	}
	static class pair implements Comparable<pair>{
		int index,val;

		public pair(int x, int y) {
			super();
			index = x;
			val = y;
		}

		@Override
		public int compareTo(pair e) {
			return val-e.val;
		}
		
	}
}
