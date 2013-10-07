package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Idempotents {

	public static int[] extendedGCD(int a, int b) {
		if (b == 0)
			return new int[] { 1, 0, a };
		int[] res = extendedGCD(b, a % b);
		int x = res[0];
		int y = res[1];
		return new int[] { y, x - y * (a / b), res[2] };
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(st.readLine());
		while(t-->0)
		{
			int num=Integer.parseInt(st.readLine());
			int n=0,m=0;
			for(int i=2;i*i<=num;i++)
				if(num%i==0)
				{
					n=i;
					m=num/n;
					break;
				}
			int[]coff=extendedGCD(n,m);
			HashSet<Integer> sol=new HashSet<Integer>();
			sol.add(0);
			sol.add(1);
			sol.add((((coff[0]*n)%num)+num)%num);
			sol.add((((coff[1]*m)%num)+num)%num);
			Integer[]res=sol.toArray(new Integer[0]);
			Arrays.sort(res);
			for(int i=0;i<res.length;i++)
				System.out.print(res[i]+(i==res.length-1?"\n":" "));
		}
	}
}
