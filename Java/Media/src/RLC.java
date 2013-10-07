

public class RLC
{
	public static Comp compress(String text)
	{
		String compressed="";
		int dupsCount=1;
		char c=' ';
		for(int i=0;i<=text.length()-2;i++)
		{
			c=text.charAt(i);
			if(c==text.charAt(i+1))
				dupsCount++;
			else
			{
				compressed+=""+c+(dupsCount>1?"!"+dupsCount:"");
				dupsCount=1;
			}
			if(i==text.length()-2)
			{
				if(dupsCount==1)
					compressed+=text.charAt(text.length()-1);
				else
					compressed+=c+"!"+dupsCount;
				break;
			}
		}
		double factor=text.length()*1.0/compressed.length();
		return new Comp(compressed,factor);
	}
}
