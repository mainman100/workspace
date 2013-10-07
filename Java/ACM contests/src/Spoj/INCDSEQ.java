package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class INCDSEQ {

	static int size=1;
	static int[][]tree;
	static int[]a;
	static int mod=5000000;
	static int MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		int n=Integer.parseInt(tok.nextToken());
		int k=Integer.parseInt(tok.nextToken());
		int[]temp=new int[n];
		a=new int[n];
		for(int i=0;i<n;i++)
		{
			a[i]=Integer.parseInt(st.readLine());
			temp[i]=a[i];
		}
		//Hashing-------------------
		Arrays.sort(temp);
		int c=1;
		HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
		map.put(temp[0],0);
		for(int i=1;i<n;i++)
		{
			if(temp[i]==temp[i-1])
				continue;
			map.put(temp[i], c++);
		}
		for(int i=0;i<n;i++)
			a[i]=map.get(a[i]);
		//-----------------------------
		while(size<n)
			size<<=1;
		tree=new int[2*size][k+1];
		for(int i=0;i<n;i++)
		{
			insert(a[i], 1, 1-tree[a[i]+size][1]);
			for(int l=2;l<=k;l++)
			{
				int sumTree=query(1,l-1,0,a[i]-1,0,size-1);
				int val=(sumTree-tree[a[i]+size][l]+mod)%mod;
				insert(a[i], l, val);
			}			
		}
		int sumTree=query(1,k,0,n-1,0,size-1);
		System.out.println(sumTree);
	}
	static void insert(int index,int k,int val)
	{
		index+=size;
		tree[index][k]=(tree[index][k]+val)%mod;
		index>>=1;
		while(index>0)
		{
			tree[index][k]=(tree[index][k]+val)%mod;
			index>>=1;
		}
	}
	static int query(int index,int k,int i,int j,int b,int e)
	{
		if(i>e||j<b)
			return 0;
		if(i<=b&&j>=e)
			return tree[index][k];
		int mid=(b+e)/2;
		int ql=query(2*index,k,i,j,b,mid);
		int qr=query(2*index+1,k,i,j,mid+1,e);
		return (ql+qr)%mod;
	}
}
