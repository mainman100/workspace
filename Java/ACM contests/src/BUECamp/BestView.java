package BUECamp;


import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BestView {


	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String data=st.readLine();
			if(data==null)
				break;
			if(data.equals(""))
				continue;
		//	System.out.println("data "+data+" has length "+data.length());
			String[]num=data.split(" ");
			int[]h=new int[num.length];
			for(int i=0;i<h.length;i++)
				h[i]=Integer.parseInt(num[i]);
			System.out.println(solve(h));
		}
	}
	public static int solve(int []h)
	{
		int[]see=new int[h.length];
		Line2D[] lines=new Line2D[h.length];
		for(int i=0;i<lines.length;i++)
			lines[i]=new Line2D.Double(i, 0, i, h[i]);
		for(int i=0;i<h.length;i++)
		{
			for(int j=0;j<h.length;j++)
			{
				if(i==j)
					continue;
				Line2D l=new Line2D.Double(i,h[i], j, h[j]);
				boolean intersect=false;
				for(int k=0;k<h.length&&!intersect;k++)
				{
					if(i==k||j==k)
						continue;
					if(l.intersectsLine(lines[k]))
						intersect=true;
				}
				if(!intersect)
					see[i]++;
			}
		}
		Arrays.sort(see);
		return see[see.length-1];
	}
}
