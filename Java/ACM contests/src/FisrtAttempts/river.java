package FisrtAttempts;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class river
{
	public static int min(int x,int y,int z,int w)
	{
		int min=x;
		if(y<min)
			min=y;
		if(z<min)
			min=z;
		if(w<min)
			min=w;
		return min;
	}
	public static int sumDigit(int n)
	{
		int sum=0;
		while(n!=0)
		{
			sum+=n%10;
			n/=10;
		}
		return sum;
	}
	public static void riverHarry(int n)
	{
		int k=n;
		int r1=1;
		int r2=3;
		int r3=9;
		int min;
		while(true)
		{
			min=min(r1,r2,r3,k);
			if(min==r1)
				r1+=sumDigit(r1);
			else if(min==r2)
				r2+=sumDigit(r2);
			else if(min==r3)
				r3+=sumDigit(r3);
			else if(min==k)
				k+=sumDigit(k);
			if(k==r1)
			{
				System.out.println("1 "+k);
				break;
			}
			if(k==r2)
			{
				System.out.println("2 "+k);
				break;
			}
			if(k==r3)
			{
				System.out.println("3 "+k);
				break;
			}
		}
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		Scanner read=new Scanner(stdin.readLine());
		int k=read.nextInt();
		for(int i=0;i<k;i++)
		{
			read=new Scanner(stdin.readLine());
			int n=read.nextInt();
			riverHarry(n);
		}
	}
}