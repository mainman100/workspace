
import java.util.ArrayList;


public class LZW 
{
	private static ArrayList<StringCode> init()
	{
		ArrayList<StringCode> list=new ArrayList<StringCode>();
	//	for(int i=32;i<=126;i++)
		//	list.add(new StringCode(""+(char)i));
		StringCode A=new StringCode("A");
		StringCode B=new StringCode("B");
		StringCode C=new StringCode("C");
		list.add(A);
		list.add(B);
		list.add(C);
		return list;
	}
	public static Comp compress(String text)
	{
		ArrayList<StringCode> dictionary=init();
		String s=text.charAt(0)+"";
		char c;
		String result="";
		int index;
		for(int i=1;i<text.length();i++)
		{
			c = text.charAt(i);
			index=dictionary.indexOf(new StringCode(s+c,0));
			if(index!=-1)
				s+=c;
			else 
			{
				index=dictionary.indexOf(new StringCode(s,0));
				result+=dictionary.get(index).getCode()+" ";
				dictionary.add(new StringCode(s+c));
				s = c+"";
			}
		}
		index=dictionary.indexOf(new StringCode(s,0));
		result+=dictionary.get(index).getCode()+" ";
		int afterLength=result.length()-Comp.occur(result,' ');
		double factor=text.length()*1.0/afterLength;
		System.out.println(dictionary);
		StringCode.setCount(1);
		return new Comp(result,factor);
	}
}
class StringCode
{
	private static int count=1;
	private String string;
	private int code;
	public StringCode(String string, int code)
	{
		this.string = string;
		this.code = code;
	}
	public StringCode(String string)
	{
		this.string = string;
		code=count++;
	}
	/**
	 * @return the string
	 */
	public String getString()
	{
		return string;
	}
	/**
	 * @return the code
	 */
	public int getCode()
	{
		return code;
	}
	public boolean equals(Object e)
	{
		StringCode sc=(StringCode)e;
		return string.equals(sc.getString());
	}
	public String toString()
	{
		return "("+string+","+code+")";
	}
	public static void setCount(int count) {
		StringCode.count = count;
	}
	public static int getCount() {
		return count;
	}
}

