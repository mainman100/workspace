package graphs;

import java.awt.geom.Line2D;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BasicWall
{
	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		StringBuffer out=new StringBuffer();
		Object []cas={0,1,"E",0,-1,"W",1,0,"S",-1,0,"N"};
		boolean[][]vis=new boolean[6][6];
		Line2D.Double[]w=new Line2D.Double[3];
		while(true)
		{
			for(int i=0;i<6;i++)
				Arrays.fill(vis[i],false);
			int sj=readInt()-1;
			int si=readInt()-1;
			if(si==-1&&sj==-1)
				break;
			int ej=readInt()-1;
			int ei=readInt()-1;
			for(int i=0;i<3;i++)
			{
				int x1=readInt();
				int y1=readInt();
				int x2=readInt();
				int y2=readInt();
				w[i]=new Line2D.Double(x1-0.1,y1-0.1,x2-0.1,y2-0.1);

			}
			Queue<node> q=new LinkedList<node>();
			q.add(new node(si, sj, ""));
			while(true)
			{
				node e=q.poll();
				int i=e.i;
				int j=e.j;
				String path=e.path;
				if(vis[i][j])
					continue;
				vis[i][j]=true;
				if(i==ei&&j==ej)
				{
					out.append(path+"\n");
					break;
				}
				for(int h=0;h<cas.length;h=h+3)
					if(i+(Integer)cas[h]>=0&&i+(Integer)cas[h]<=5&&j+(Integer)cas[h+1]>=0&&j+(Integer)cas[h+1]<=5)
					{
						Line2D.Double line=new Line2D.Double(j,i,j+(Integer)cas[h+1],i+(Integer)cas[h]);
						if(!line.intersectsLine(w[0])&&!line.intersectsLine(w[1])&&!line.intersectsLine(w[2]))
							q.add(new node(i+(Integer)cas[h],j+(Integer)cas[h+1],path+(String)cas[h+2]));	
					}
			}
		}
		System.out.print(out);
	}
	static class node
	{
		int i,j;
		String path;
		public node(int i, int j, String path) {
			super();
			this.i = i;
			this.j = j;
			this.path = path;
		}
	}
}

