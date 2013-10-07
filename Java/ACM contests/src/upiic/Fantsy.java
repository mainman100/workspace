package upiic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Fantsy 
{


	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
	int t=Integer.parseInt(stdin.readLine());
	int n,k,mod,res;
	int[]a;
	int[]count;
	String[]temp;
	for(int i=0;i<t;i++)
	{
		StringTokenizer nums=new StringTokenizer(stdin.readLine());
		n=Integer.parseInt(nums.nextToken());
		k=Integer.parseInt(nums.nextToken());
		mod=Integer.parseInt(nums.nextToken());
		temp=stdin.readLine().split(" ");
		a=new int[temp.length];
		for(int j=0;j<temp.length;j++)
			a[j]=Integer.parseInt(temp[j]);
		count=new int[k];
		res=0;
		while(true)
		{
			for(int m=0;m<k;m++)
				res+=a[count[m]];
			res=res%mod;
			if(addOne(count,n,k)==false)
			{
				out.append("Case "+(i+1)+": "+res+"\n");
				break;
			}
		}
	}
	System.out.print(out);
	}
	static boolean addOne(int[]count,int n,int k)
	{
		count[count.length-1]++;
		for(int i=k-1;i>=0;i--)
		{
			if(count[i]==n)
			{
				if(i==0)
					return false;
				count[i]=0;
				count[i-1]++;
			}
			else
				break;
		}
		return true;
	}
}
