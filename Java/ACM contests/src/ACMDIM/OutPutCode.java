package ACMDIM;

public class OutPutCode {

	static String p="abaaba";
	static int n=0;
	public static void printCode()
	{
		int M = p.length();
		int j = -1;
		int[] b = new int[M];
		b[0] = -1;
		for (int i = 1; i < M; i++) {
			while (j > -1 && p.charAt(i) != p.charAt(j + 1))
				j = b[j];
			if (p.charAt(i) == p.charAt(j + 1))
				j++;
			b[i] = j;
		}
		//------------------------------------
		String s="";
		s+="public static void trace(char c){\n";
		s+="if(p.charAt(n)==c)\n";
		s+="   n++;\n";
		for(int i=1;i<M-1;i++)
		{
			if(M>2)
				s+="else ";
			s+="if(n=="+(i+1)+"){\n";
			int start=b[i];
			j=b[i];
			while(j>=0)
			{
				if(j!=start)
					s+="else ";
				s+="if(c=='"+p.charAt(j+1)+"')\n";
				s+="   n="+(j+2)+";\n";
				j=b[j];
			}
			if(start!=-1)
				s+="else\n";
			s+="    n=0;\n";
			s+="}\n";
		}
		s+="else if(n==1)\n";
		s+="  n=0;\n";
		s+="if(n==0&&c=='"+p.charAt(0)+"')\n";
		s+="    n=1;\n";
		s+="System.out.println(n);\n";
		s+="if(n=="+M+")\n";
		j=b[M-1]+1;
		s+="  n="+j+";\n";
		s+="}";
		System.out.println(s);
	}
	public static void main(String[]args)
	{
	//	String s="abababac";
		printCode();
	//	for(int i=0;i<s.length();i++)
		//	trace(s.charAt(i));
	}



}
