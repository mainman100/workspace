package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {


	public static void main(String[] args) throws NumberFormatException,
			IOException {


		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(st.readLine());
		int res = sol(q);
		if (res == -1)
			System.out.println("No solution");
		else
			System.out.println(res);
		
	}
	public static int sol(int q)
	{
		if(q==0)
			return 1;
		int start=4*q,end=5*q;
		int res=-1;
		while(end>=start)
		{
			int mid=(start+end)/2;
			int zeros=zeros(mid);
			if(zeros==q)
				res=mid;
			if(zeros>=q)
				end=mid-1;
			else
				start=mid+1;
		}
		if(res!=-1)
			return res;
		return -1;
	}
	public static int zeros(int n)
	{
		int res=0;
		for(int m=5;m<=n;m*=5)
			res+=n/m;
		return res;
	}
}