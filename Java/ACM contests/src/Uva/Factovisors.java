package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Factovisors {

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String data=st.readLine();
			if(data==null||data.length()==0)
				break;
			StringTokenizer tok=new StringTokenizer(data);
			int n=Integer.parseInt(tok.nextToken());
			int m=Integer.parseInt(tok.nextToken());
			String res=divide(n, m)?" divides ":" does not divide ";
			System.out.println(m+res+n+"!" );
		}
	}
	public static boolean divide(int n,int m)
	{
		if(m==0)
			return false;
		if(n==0)
			n=1;
		for(int i=2;i*i<=m;i++)
		{
			if(m%i==0)
			{
				int e1=0;
				while(m%i==0)
				{
					e1++;
					m/=i;
				}
				int e2=0;
				int power=i;
				while(power<=n)
				{
					e2+=n/power;
					power*=i;
				}
				if(e1>e2)
					return false;
			}
		}
		if(m>1&&m>n)
			return false;
		return true;
	}
}
