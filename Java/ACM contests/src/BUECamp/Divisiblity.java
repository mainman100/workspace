package BUECamp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Divisiblity {

	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String s=st.readLine();
			if(s.equals("end"))
				break;
			long count=0;
			for(int i=0;i<s.length();i++)
			{
				char c=s.charAt(i);
				int n=getInt(c);
				count+=n;
			}
			if(count%61==0)
				System.out.println("yes");
			else 
				System.out.println("no");
		}
	}
	public static int getInt(char c)
	{
		if(c>='0'&&c<='9')
			return c-'0';
		if(c>='A'&&c<='Z')
			return c-'A'+10;
		return c-'a'+36;
	}
}
