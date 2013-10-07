import java.io.*;
public class date
{
	int day,month,year;
	public static boolean check(int day,int month,int year)//Check whether the date exist
	{
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
		{
			return day>=1&&day<=31;
		}
		else if(month==2)
		{
			if(year%4!=0)
				return day>=1&&day<=28;
			else
				return day>=1&&day<=29;
		}
		else if(month==4||month==6||month==9||month==11)
			return day>=1&&day<=30;
		else
			return false;
	}
	public date(String s)//Construct an object that has the details of the date
	{
		String []date={"","",""};
		int count=0;
		int i=0;
		do
		{
			if(s.charAt(i)!='/')
				date[count]+=s.charAt(i);
			else
				count++;
			i++;
		}while(i<s.length());
		 day  =Integer.parseInt(date[0]);
		 month=Integer.parseInt(date[1]);
		 year =Integer.parseInt(date[2]);
	}
	public date()//A default constructor
	{
	}
	public static int Months_days_diff(date s)//Number of days from the beginning of the specified
											 //year until the day in the specified year
	{
		int diff=0;
		switch(s.month)
		{
			case 12:diff+=30;
			case 11:diff+=31;
			case 10:diff+=30;
			case 9:diff+=31;
			case 8:diff+=31;
			case 7:diff+=30;
			case 6:diff+=31;
			case 5:diff+=30;
			case 4:diff+=31;
			case 3:diff+=28;
			case 2:diff+=31;
			case 1:diff+=0;
		}
		if(s.month>2)
			diff+=(s.year%4!=0?0:1);
		diff+=s.day;
		return diff;
	}
	public static int All_diff(date s)//calculate the number of days
	{
		int diff=0;
		if(s.year>2008)
		{
			int n=s.year-2008;
			int x=n-n%4;
			diff+=(int)(0.75*x*365+(x/4)*366+(n%4!=0?(((n%4)-1)*365+366):0));
			diff+=Months_days_diff(s);
		}
		else
		{
			int n=2008-s.year;
			int x=n-n%4;
			diff+=(int)(0.75*x*365+(x/4)*366+n%4*365);
			diff-=Months_days_diff(s);
			diff*=-1;
		}
		return diff;
	}
	public static void main(String[]args)throws IOException
	{
		System.out.println("Note:This calender works only for a date that"+
		" lies between 28/2/1900 and 1/3/2100");
		String flag="yes";
		while(flag.toLowerCase().equals("yes"))
		{
		date test=new date();
		int i=0;
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
	do
	{
		System.out.println((i>0?"THERE IS NO SUCH DATE,YOU FOOL!"+
		"Try again!":"Enter the date as follows:Day/Month/Year"));	
		test=new date(input.readLine());
		i++;		
	}while(check(test.day,test.month,test.year)==false);
		int diff=All_diff(test);
		diff%=7;
		if(diff<0)
			diff+=7;
		String date="";
		switch(diff)
		{
			case 1:date="Tuesday";break;
			case 2:date="Wednesday";break;
			case 3:date="Thursday";break;
			case 4:date="Friday";break;
			case 5:date="Saturday";break;
			case 6:date="Sunday";break;
			case 0:date="Monday";break;
		}
		System.out.println("This date is at "+date);
		System.out.println("Try again?Enter \"yes\",otherwise write any ting else !");
		flag=input.readLine();
		}
		System.out.println("Thank you and good by .");
	}
}