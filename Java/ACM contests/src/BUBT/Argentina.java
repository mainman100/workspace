package BUBT;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Argentina {

	static StreamTokenizer st;
	private static int ni() throws IOException
	{
		st.nextToken();
		return (int)st.nval;
	}
	private static String ns() throws IOException
	{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[]args) throws IOException
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=ni();
		for(int k=1;k<=t;k++)
		{
			player[] a=new player[10];
			for(int i=0;i<10;i++)
			{
				String _name=ns();
				int _at=ni();
				int _de=ni();
				a[i]=new player(_at, _de, _name);
			}
			Arrays.sort(a);
			String []at=new String[5];
			String []def=new String[5];
			for(int i=0;i<5;i++)
			{
				at[i]=a[i].name;
				def[i]=a[i+5].name;
			}
			Arrays.sort(at);
			Arrays.sort(def);
			String att="(",deff="(";
			for(int i=0;i<4;i++)
			{
				att+=at[i]+", ";
				deff+=def[i]+", ";
			}
			att+=at[4]+")";
			deff+=def[4]+")";
			System.out.println("Case "+k+":\n"+att+"\n"+deff);
		}

	}
	static class player implements Comparable<player>
	{
		int attack,defence;
		String name;
		public player(int attack, int defence, String name) {
			super();
			this.name = name;
			this.attack = attack;
			this.defence = defence;
		}
		@Override
		public int compareTo(player e) {
			if(attack>e.attack)
				return -1;
			else if(attack<e.attack)
				return 1;
			else if(defence>e.defence)
				return 1;
			else if(defence<e.defence)
				return -1;
			return name.compareTo(e.name);
		}
		
	}
}
