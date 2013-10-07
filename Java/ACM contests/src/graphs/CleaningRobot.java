package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class CleaningRobot {
	
	static char[][]map;
	static boolean[][][][]wall;
	static int h,w;
	public static void main(String[]args) throws IOException
	{
		BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String[] begin=stdin.readLine().split(" ");
			w=Integer.parseInt(begin[0]);
			h=Integer.parseInt(begin[1]);
			if(w==0&&h==0)
				break;
			map=new char[h][w];
			wall=new boolean[h][w][h][w];
			int bi=0,bj=0,dcount=0;
			for(int i=0;i<h;i++)
			{
				String s=stdin.readLine();
				for(int j=0;j<w;j++)
				{
					char c=s.charAt(j);
					map[i][j]=c;
					if(c=='o')
					{
						bi=i;
						bj=j;
					}
					else if (c=='*')
						dcount++;
					else if(c=='x')
					{
						if(i!=0)
						{
							wall[i][j][i-1][j]=true;
							wall[i-1][j][i][j]=true;
						}
						if(i!=h-1)
						{
							wall[i][j][i+1][j]=true;
							wall[i+1][j][i][j]=true;
						}
						if(j!=0)
						{
							wall[i][j][i][j-1]=true;
							wall[i][j-1][i][j]=true;
						}
						if(j!=w-1)
						{
							wall[i][j][i][j+1]=true;
							wall[i][j+1][i][j]=true;
						}
					}
				}
			}
			System.out.println(dijkstra(bi,bj,dcount));
		}
	}

	private static int dijkstra(int bi, int bj,int dirtycount) {
		
	//	System.out.println(bi+" "+bj+" "+dirtycount);
		PriorityQueue<node> q=new PriorityQueue<node>();
		boolean [][][]v=new boolean[h][w][dirtycount+1];
		byte[]cases={0,1,0,-1,1,0,-1,0};
		q.add(new node(bi, bj, 0, dirtycount,"("+bi+","+bj+")"));
		while(!q.isEmpty())
		{
			node e=q.poll();
			int i=e.i;
			int j=e.j;
			int cost=e.cost;
			int dirty=e.dirty;
			String path=e.path;
			boolean[][] wt=e.wt;
			if(v[i][j][dirty]||wt[j][j])
				continue;
			v[i][j][dirty]=true;
			wt[i][j]=true;
			if(map[i][j]=='*')
				dirty--;
			if(dirty==0)
			{
				System.out.println(path);
				return cost;				
			}
			for(int g=0;g<cases.length;g+=2)
			{
				int ni=cases[g]+i;
				int nj=cases[g+1]+j;
				if(ni>=0&&ni<h&&nj>=0&&nj<w&&!v[ni][nj][dirty])
				{
					if(wall[i][j][ni][nj]==false)
						q.add(new node(ni, nj, cost+1, dirty,path+"("+ni+","+nj+")"));
				}
			}		
		}
		return -1;
	}
	static class node implements Comparable<node>
	{
		int i,j,cost,dirty;
		String path;
		boolean [][]wt=new boolean[h][w];
		public node(int i, int j, int cost, int dirty) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
			this.dirty = dirty;
		}

		public node(int i, int j, int cost, int dirty, String path) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
			this.dirty = dirty;
			this.path = path;
		}

		@Override
		public int compareTo(node e) {
			return cost-e.cost;
		}
		
	}
}
