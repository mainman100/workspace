package ANARC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NotSoFlat {

	static int max=1000000;
	static int[]p=new int[max+1];
	static
	{
		for(int i=2;i<=1000;i++)
		{
			if(p[i]==0)
			{
				p[i]=i;
				for(int j=i*i;j<=max;j+=i)
					if(p[j]==0)
						p[j]=i;
			}
		}
		for(int i=1001;i<=max;i++)
			if(p[i]==0)
				p[i]=i;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok;
		int t=1;
		StringBuffer out=new StringBuffer();
		while(true)
		{
			tok=new StringTokenizer(st.readLine());
			int n=Integer.parseInt(tok.nextToken());
			int m=Integer.parseInt(tok.nextToken());
			if(n==0&&m==0)
				break;
			int s=0,moves=0;
			int f,e1,e2;
			while(n>1)
			{
				s++;
				f=p[n];
				e1=0;
				e2=0;
				while(n%f==0)
				{
					e1++;
					n/=f;
				}
				while(m%f==0)
				{
					e2++;
					m/=f;
				}
				moves+=Math.abs(e1-e2);
			}
			while(m>1)
			{
				s++;
				f=p[m];
				while(m%f==0)
				{
					moves++;
					m/=f;
				}
			}
			out.append(t+". "+s+":"+moves+"\n");
			t++;
		}
		System.out.print(out);
	}
}
