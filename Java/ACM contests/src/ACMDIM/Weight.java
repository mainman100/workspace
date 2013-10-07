package ACMDIM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Weight {

	static boolean[][]w;
	static int[]v;
	static int WHITE=0;
	static int GRAY=1;
	static int BLACK=2;
	static int n;
	static boolean hasCycle=false;
	static LinkedList<Integer> out=new LinkedList<Integer>();
	public static void dfsRec(int i)
	{
		if(v[i]==GRAY)
		{
			hasCycle=true;
			return;
		}
		if(v[i]==BLACK)
			return;
		v[i]=GRAY;
		for(int j=0;j<n;j++)
			if(w[i][j])
			{
				dfsRec(j);
				if(hasCycle)
					return;
			}
		v[i]=BLACK;
		out.addFirst(i);
	}
	public static void dfsIter(int start)
	{
		Stack<Integer> stack=new Stack<Integer>();
		v[start]=GRAY;
		stack.push(start);
		while(!stack.isEmpty())
		{
			int i=stack.peek();
			boolean hasChild=false;
			for(int j=0;j<n&&!hasChild;j++)
			{
				if(w[i][j])
				{
					if(v[j]==GRAY)
					{
						hasCycle=true;
						return;
					}
					if(v[j]==BLACK)
						continue;
					v[j]=GRAY;
					stack.push(j);
					hasChild=true;
				}
			}
			if(!hasChild)
			{
				v[i]=BLACK;
				out.addFirst(stack.pop());
			}
		}
	}
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		n=Integer.parseInt(tok.nextToken());
		int m=Integer.parseInt(tok.nextToken());
		w=new boolean[n][n];
		v=new int[n];
		for(int i=0;i<m;i++)
		{
			tok=new StringTokenizer(st.readLine());
			int x=Integer.parseInt(tok.nextToken())-1;
			int y=Integer.parseInt(tok.nextToken())-1;
			w[x][y]=true;
		}
		for(int i=0;i<n;i++)
		{
			if(v[i]==WHITE)
				dfsRec(i);
			if(hasCycle)
			{
				System.out.println("No solution");
				break;
			}
		}
		if(!hasCycle)
		{
			int[]res=new int[n];
			int count=1;
			for(int j:out)
				res[j]=count++;
			for(int i=0;i<n;i++)
				System.out.print(res[i]+(i==n-1?"\n":" "));	
		}
	}
}
