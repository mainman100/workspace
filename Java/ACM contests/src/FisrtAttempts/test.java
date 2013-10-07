package FisrtAttempts;
public class test 
{
	public static long f(long n)
	{
	      long res = 0;
	     for( int i= 1; i<= n;i++ ){
	            res = (res + n/i);
	      }
	     return res;
	}
	public static void main(String[]args)
	{
		System.out.println(f(100000000));
	}
}
