import java.util.Arrays;


public class BinaryCode
{
	public String[] decode(String s)
	{
		int[] p=new int[s.length()+2];
		int []q=new int[s.length()];
		String res1="",res2="";
		for(int i=0;i<s.length();i++)
			q[i]=s.charAt(i)-'0';
		p[1]=0;
		for(int i=0;i<q.length;i++)
			if(q[i]<0||q[i]>3)
				return new String[]{"NONE","NONE"};
		for(int i=1;i<p.length-2;i++)
		{
			p[i+1]=q[i-1]-p[i-1]-p[i];
			if(p[i+1]>1||p[i+1]<0)
			{
				res1="NONE";
				break;
			}
		}
		int j=p.length-3;
		if(!(q[j]==p[j]+p[j+1]))
			res1="NONE";
		if(res1.length()==0)
			for(int i=1;i<p.length-1;i++)
				res1=res1+p[i];
		Arrays.fill(p,0);
		p[1]=1;
		for(int i=1;i<p.length-2;i++)
		{
			p[i+1]=q[i-1]-p[i-1]-p[i];
			if(p[i+1]>1||p[i+1]<0)
			{
				res2="NONE";
				break;
			}
			if(i==p.length-3)
				if(!(q[i]==p[i]+p[i+1]))
					res2="NONE";
		}
		j=p.length-3;
		if(!(q[j]==p[j]+p[j+1]))
			res2="NONE";
		if(res2.length()==0)
			for(int i=1;i<p.length-1;i++)
				res2=res2+p[i];
		return new String[]{res1,res2};
	}
	public static void main(String[]args)
	{
		String[]res=new BinaryCode().decode("123210120");
		System.out.println(res[0]+","+res[1]);
	}
}
