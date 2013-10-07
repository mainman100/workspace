package FisrtAttempts;
public class coins
{
	public static int max(int n)
	{
		if(n<12)
			return n;
		return max(n/2)+max(n/3)+max(n/4);
	}
	public static void main(String[]args)
	{
		System.out.println(max(2));
	}
}