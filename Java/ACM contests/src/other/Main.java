package other;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;


public class Main
{
	public static void main (String[]args)throws Exception
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		int n=Integer.parseInt(stdin.readLine());
		ArrayList<String> topCards;
		ArrayList<String> botCards;
		String[] array;
		for(int i=1;i<=n;i++)
		{
			String text=stdin.readLine();
			array=text.split(" ");
			int y=0;
			int x;
			topCards=new ArrayList<String>(25);
			botCards=new ArrayList<String>(27);
			for(int j=0;j<25;j++)
				botCards.add(array[j]);
			for(int j=25;j<52;j++)
				topCards.add(array[j]);
			for(int w=0;w<3;w++)
			{
				String card=botCards.get(botCards.size()-1);
				x=card.charAt(0)-'0';
				if(x<2||x>9)
					x=10;
				y+=x;
				int rem=10-x+1;
				for(int j=0;j<rem;j++)
					botCards.remove(botCards.size()-1);
			}
			botCards.addAll(topCards);
			out.append("Case "+i+": "+ botCards.get(y-1)+"\n");
		}
		System.out.print(out);
	}
}
