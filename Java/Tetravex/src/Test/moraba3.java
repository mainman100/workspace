package Test;

public class moraba3 extends figure
{
	public double area() 
	{
		return 0;
	}
	public void A1()
	{
		try
		{
			A2();			
		}
		catch(Exception e)
		{
			System.out.println("A1");
		}
	}
	public void A2()
	{
		try
		{
			A3();			
		}
		catch(Exception e)
		{
			System.out.println("A2");
		}
	}
	public void A3()
	{
		int x=3/0;
	}
	public static void main(String args[]) {
	try 
	{
		int a = 1;
		int b = 42 / a;
		System.out.println("a = " + a);
		try 
		{ // nested try block
			if(a==1) 
				a = a/0; // division by zero
			if(a==2)
			{
				int c[] = { 1 };
				c[42] = 99; // generate an out-of-bounds exception
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array index out-of-bounds: " + e);
		}
		System.out.println("It will not execute,biso");
	}
	catch(ArithmeticException e)
		{
		System.out.println("Divide by 0: " + e);
		}
		}
}
