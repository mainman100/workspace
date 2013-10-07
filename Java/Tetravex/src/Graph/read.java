package Graph;
public class read 
{
	static int second;
	private String s;
	public read(String S)
	{
		s=S+" ";
	}
	public int nextInt()//get the next integer from a string
	{
		String num="";
		int i;
		for(i=0;i<s.length();i++)
		{
			int x=s.charAt(i)-'0';
			if(x>=0&&x<=9)
			{
				num+=x;
				if(i!=0&&s.charAt(i-1)=='-')
					return -1;	
			}
			else if(num.length()>0)
			{
				s=s.substring(i+1);
				return Integer.parseInt(num);	
			}
		}
		return -1;
	}
	public int numOfInt()//calculates the number of integers in a string
	{
		int count=0;
		String num="";
		boolean afterNumber=false;
		int i;
		for(i=0;i<s.length();i++)
		{
			int x=s.charAt(i)-'0';
			if(x>=0&&x<=9)
			{
				num+=x;
				afterNumber=true;
			}
			else if(afterNumber==true)
					{
						afterNumber=false;
						count++;	
					}
		}
		return count;
	}
	public void setString(String f)
	{
		s=f+" ";
	}
	public static String time(int t)//return time in format hours:minutes:seconds
									//given the total time in seconds
	{
		int hours=t/3600;
		int minutes=t%3600/60;
		int seconds=t%3600%60;
		String h=hours+":",m=minutes+":";
		second=seconds;
		return h+m+seconds;
	}
	public String getString()
	{
		return s;
	}		
}