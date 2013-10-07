package timus;

import java.io.IOException;
import java.util.Scanner;

public class WomenAge {

	static int getNum(char c)
	{
		int n=c-'0';
		if(n>=0&&n<=9)
			return n;
		return(c-'A')+10;
	}
	public static void main(String[] args) throws IOException {
		long sumDigits=0;
		String num=new Scanner(System.in).next();
		int maxDigit=-1;
		for(int i=0;i<num.length();i++)
		{
			sumDigits+=getNum(num.charAt(i));
			maxDigit=Math.max(maxDigit, getNum(num.charAt(i)));
		}
		if(maxDigit==0)
			maxDigit=1;
		boolean found=false;
		for(int k=maxDigit+1;k<=36&&!found;k++)
			if(sumDigits%(k-1)==0)
			{
				System.out.println(k);
				found=true;
			}
		if(!found)
			System.out.println("No solution.");
	}
}
