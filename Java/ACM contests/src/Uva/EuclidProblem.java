package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EuclidProblem {

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String data=st.readLine();
			if(data==null||data.length()==0)
				break;
			String[]in=data.split(" ");
			int a=Integer.parseInt(in[0]);
			int b=Integer.parseInt(in[1]);
			if(a==b)
			{
				System.out.println("0 1 "+a);
				continue;
			}
			int[]coff=extendedGCD(a, b);
			int d=coff[2];
			int[]cyc={b/d,a/d};
			int i=b/d>a/d?0:1;
			
			int c1pos=(coff[i]%cyc[i]+cyc[i])%cyc[i];
			int t=(c1pos-coff[i])/cyc[i];
			int c2pos=cyc[1-i]*-t+coff[1-i];
			int sumPos=Math.abs(c1pos)+Math.abs(c2pos);
			
			int c1neg=c1pos-cyc[i];
			int c2neg=c2pos+cyc[1-i];
			int sumNeg=Math.abs(c1neg)+Math.abs(c2neg);

			if(sumNeg<sumPos)
			{
				c1pos=c1neg;
				c2pos=c2neg;
			}
			int x=c1pos,y=c2pos;
			if(i==1)
			{
				x^=y;
				y^=x;
				x^=y;
			}
			System.out.println(x+" "+y+" "+d);
		}
	}
	public static int[] extendedGCD(int a, int b) {
		if (b == 0)
			return new int[] { 1, 0, a };
		int[] res = extendedGCD(b, a % b);
		int x = res[0];
		int y = res[1];
		return new int[] { y, x - y * (a / b), res[2] };
	}
}
