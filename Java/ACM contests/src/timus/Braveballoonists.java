package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Braveballoonists {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		int[]primes=new int[10001];
		for(int t=0;t<10;t++)
		{
			int n=Integer.parseInt(st.readLine());
			for(int i=2;i*i<=n;i++)
			{
				if(n%i==0)
				{
					primes[i]++;
					n/=i;
					i--;
				}
			}
			if(n>1)
				primes[n]++;
		}
		int sum=1;
		for(int i=2;i<=10000;i++)
			sum=(sum*(primes[i]+1))%10;
		System.out.println(sum);
	}
}
