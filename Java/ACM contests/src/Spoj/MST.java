package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class MST {

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tok=new StringTokenizer(st.readLine());
		int n=Integer.parseInt(tok.nextToken());
		int m=Integer.parseInt(tok.nextToken());
		LinkedList<Pair>[]adj=new LinkedList[n];
		for(int i=0;i<n;i++)
			adj[i]=new LinkedList<Pair>();
		for(int i=0;i<m;i++)
		{
			tok=new StringTokenizer(st.readLine());
			int u=Integer.parseInt(tok.nextToken())-1;
			int v=Integer.parseInt(tok.nextToken())-1;
			int cost=Integer.parseInt(tok.nextToken());
			adj[u].add(new Pair(v, cost));
			adj[v].add(new Pair(u, cost));

		}
		PriorityQueue<Pair> pq=new PriorityQueue<Pair>();
		pq.add(new Pair(0,0));
		boolean[]added=new boolean[n];
		int numAdded=0;
		long totalCost=0;
		while(numAdded<n)
		{
			Pair node=pq.poll();
			int v=node.v;
			if(added[v])
				continue;
			totalCost+=node.cost;
			added[v]=true;
			numAdded++;
			for(Pair pair:adj[v])
				if(!added[pair.v])
					pq.add(new Pair(pair.v, pair.cost));
		}
		System.out.println(totalCost);
	}
	static class Pair implements Comparable<Pair> {
		int v;
		int cost;

		public Pair(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		public int compareTo(Pair e) {
			return cost - e.cost;
		}
	}
}

class Kruskal{
	//As an exercise Try to out put the edges used for the MST
	/*
	 * Sample input:
	 * On the first line V and E the number of Nodes and edges respectively
	 * E lines each corresponding to an edge in the graph
	 * a,b (the vertices of the edge and the cost) for each line 
	 * 4 5
	   1 2 10
       2 3 15
       1 3 5
       4 2 2
       4 3 40
       Sample Output:
       17
	 */
	public static void main(String[] args) throws IOException{
		BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in)) ;
		StringTokenizer st = new StringTokenizer(rdr.readLine()) ;
		int V = new Integer(st.nextToken()) ;
		DisjointSet dsd = new DisjointSet(V) ;
		int E = new Integer(st.nextToken()) ;
		Edge[]edges = new Edge[E] ;
		for(int i = 0 ; i < E ; i++)
		{
			st = new StringTokenizer(rdr.readLine()) ;
			int a = new Integer(st.nextToken()) - 1 ;
			int b = new Integer(st.nextToken()) - 1 ;
			int cost = new Integer(st.nextToken()) ;
			edges[i] = new Edge(a, b, cost) ;
 		}
		Arrays.sort(edges) ;
		long res = 0 ;
		//we can add a boolean array to find which edges are taken if
		//you want to find the set of edges to take
		int e = 0 ;//number of edges taken so far as part of the MST
		for(int i = 0 ; i < E && e < V-1 ; i++)
		{
			int a = edges[i].a ;
			int b = edges[i].b ;
			int cost = edges[i].cost ;
			int ra = dsd.find(a) ;
			int rb = dsd.find(b) ;
			if(ra != rb)//Check if these are two different sets
			{
				res += cost ;
				dsd.union(ra, rb) ;//make them as one set/tree
				//mark the edge as taken if you are using an extra array
				//for the edges used in the construction
				e++ ;
			}
		}
		System.out.println(res);
	}
}
class Edge implements Comparable<Edge>
{
	int a,b,cost ;
	public Edge(int x,int y,int c){
		a = x ;
		b = y ;
		cost = c ;
	}
	public int compareTo(Edge e){
		return cost - e.cost ;//No need for 3 different conditions subtraction
		//yields the required behavior 
	}
}
class DisjointSet {
	int[]parent ;
	int[]rank;
	//Initialize each node to itself as a parent
	public DisjointSet(int size){
		parent = new int[size] ;
		rank=new int[size];
		for(int i = 0 ; i < size ; i++)
			parent[i] = i ;
	}
	//joining two sets by having one of the nodes parent as the id of the set
	//of the other
	public void union(int x,int y)
	{
		link(find(x),find(y));
	}
	private void link(int x,int y)
	{
		if(rank[x]>rank[y])
			parent[y]=x;
		else
			parent[x]=y;
		if(rank[x]==rank[y])
			rank[y]++;
	}
	//find with path compression set always the node id to the id of the whole
	//set rather than having it in a linear fashion
	//guarantees O(Log(N)) complexity for find and join 
	public int find(int n){
		if(n != parent[n])
			parent[n] = find(parent[n]) ;
		return parent[n] ;
	}
}


