package Testing;
import java.util.LinkedList;

public class Graph
{
	int size;
	int maxNum;
	square squareList[];
	int adjMatrix[][];
	Board stack;
	public int fact(int n)
	{
		if(n==0)
			return 1;
		return n*fact(n-1);
	}
	public Graph(Board c)
	{
		size=c.getsize();
		maxNum=size*size;
		squareList=new square[maxNum];//vertex list
		adjMatrix=new int[maxNum][maxNum];//Adj Matrix
		stack=new Board(size);//New empty board	
	}
	public static void main(String[]args)
	{
		for(int i=0;i<1000000;i++)
			System.out.println(i);
	}
}