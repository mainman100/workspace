package FisrtAttempts;
public class flich
{
	static int[]res=new int[1000000];
	public static int f(int n)
	{
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if(n==2)
			return 4;
		if(n==3)
			return 5;
		if(n==4)
			return 10;
		//if(n==5)
			//return ;
		return 2*f(n-1)-1+2*f(n-2)-1-2*f(n-3)+4*f(n-3)-2-2*f(n-4)-4*f(n-5)  ;
	}
	public static void main(String[]args)
	{
		System.out.println(f(13));
	}
}