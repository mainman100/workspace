package Spoj;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Stack;


public class ProjectManagement {

	static StreamTokenizer st;
	static boolean []v;
	static int n;
	static boolean[][]adj;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	@SuppressWarnings("deprecation")
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		n=r();
		int m=r();
		adj=new boolean[n][n];
		while(m-->0)
		{
			int i=r();
			int k=r();
			while(k-->0)
			{
				int j=r();
				adj[i-1][j-1]=true;
			}
		}
		v=new boolean[n];
		LinkedList<Integer> sorted=new LinkedList<Integer>();
		for(int i=0;i<n;i++)
			if(!v[i])
				DFS(i,sorted);
		Integer[]res=sorted.toArray(new Integer[0]);
		for(int num:res)
			System.out.print((num+1)+" ");
		System.out.println();
	}
	public static void DFS(int start,LinkedList<Integer> sorted)
	{
		Stack<Integer> stack=new Stack<Integer>();
		v[start]=true;
		stack.push(start);
		while(!stack.isEmpty())
		{
			int i=stack.peek();
			boolean added=false;
			for(int j=0;j<n;j++)
			if(adj[i][j]&&!v[j])
			{
				stack.push(j);
				v[j]=true;
				added=true;
				break;
			}
			if(!added)
			{
				i =stack.pop();
				sorted.addLast(i);
			}
		}
	}
}
