package ACMDIM;

import java.util.LinkedList;
import java.util.Scanner;
public class GraphBFS{
	public static void main(String[] args) {
		Scanner cs = new Scanner(System.in) ;
		int n = new Integer(cs.nextInt()) ;
		int m = new Integer(cs.nextInt()) ;
		boolean[][][]g = new boolean[n][n][4] ;
		for(int i = 0 ; i < m ; i++)
		{
			int x = cs.nextInt()-1 ;
			int y = cs.nextInt()-1 ;
			int c = cs.nextInt() ;
			g[x][y][c] = true ;
		}
		LinkedList<State> q = new LinkedList<State>() ;
		q.add(new State(0,0,0)) ;
		boolean[][]vis = new boolean[n][4] ;
		int res = -1 ;
		while(!q.isEmpty())
		{
			State top = q.removeFirst() ;
			if(vis[top.x][top.prevc])
				continue ;
			vis[top.x][top.prevc] = true ;
			if(top.x == n-1)
			{
				res = top.cost ;
				break ;
			}
			for(int i = 0 ; i < n ; i++)
				for(int j = 0 ; j < 4 ; j++)
					if(g[top.x][i][j] && j != top.prevc)
						q.add(new State(i,j,top.cost+1)) ;
		}
		System.out.println(res);
	}
}
class State
{
	int x,prevc,cost ;

	public State(int x, int prevc, int cost) {
		super();
		this.x = x;
		this.prevc = prevc;
		this.cost = cost;
	}

}

