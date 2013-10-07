package BUECamp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class PowerString {

	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String tr=st.readLine().trim();
			if(tr.equals("."))
				break;
			System.out.println(f(tr));
		}
	}
	public static int f(String s)
	{
		int l=s.length();
		for(int i=1;i<=s.length();i++)
			if(l%i==0)
			{
				boolean flag=true;
				String sub1=s.substring(0,i);
				for(int j=i;j<s.length();j+=i)
				{
					String sub2=s.substring(j,j+i);
					if(!sub1.equals(sub2))
					{
						flag=false;
						break;
					}
				}
				if(flag)
					return l/i;
			}
		return 1;
	}
}
