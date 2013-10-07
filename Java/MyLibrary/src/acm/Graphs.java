package acm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graphs {

	static short WHITE = 0;
	static short GRAY = 1;
	static short BLACK = 2;
	static short[] v;
	static boolean hasCycle = false;
	static int[][] adj;
	static int n;

	/*
	 * Detect cycle in directed graph
	 */
	public static void dfsDirectedCycle(int i) {
		if (hasCycle)
			return;
		if (v[i] == BLACK)
			return;
		if (v[i] == GRAY) {
			hasCycle = true;
			return;
		}
		v[i] = GRAY;
		for (int j = 0; j < adj[i].length; j++)
			if (adj[i][j] != -1)
				dfsDirectedCycle(j);
		v[i] = BLACK;
	}

	/*
	 * Detect cycle in non directed graph, should be called with from = -1
	 */
	public static void dfsNonDirectedCycle(int i, int from) {
		if (hasCycle)
			return;
		if (v[i] == BLACK)
			return;
		if (v[i] == GRAY) {
			hasCycle = true;
			return;
		}
		v[i] = GRAY;
		for (int j = 0; j < adj[i].length; j++)
			if (adj[i][j] != -1 && j != from)
				dfsNonDirectedCycle(j, i);
		v[i] = BLACK;
	}

	public static int BFS(int source, int destination) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(source, 0));
		boolean[] v = new boolean[n];
		while (!q.isEmpty()) {
			Pair node = q.poll();
			int u = node.v;
			if (v[u])
				continue;
			v[u] = true;
			int cost = node.cost;
			if (u == destination)
				return cost;
			for (int i = 0; i < adj[u].length; i++)
				if (adj[u][i] != -1)
					q.add(new Pair(i, cost + 1));
		}
		return -1;
	}

	public static int Dijkstra(int source, int destination) {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.add(new Pair(source, 0));
		boolean[] v = new boolean[n];
		while (!q.isEmpty()) {
			Pair node = q.poll();
			int u = node.v;
			if (v[u])
				continue;
			v[u] = true;
			int cost = node.cost;
			if (u == destination)
				return cost;
			for (int i = 0; i < adj[u].length; i++)
				if (adj[u][i] != -1)
					q.add(new Pair(i, cost + adj[u][i]));// can be optimized by
															// checking before
															// adding
		}
		return -1;
	}

	public static long prim(LinkedList<Pair> adj[]) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(0, 0));
		boolean[] added = new boolean[n];
		int numAdded = 0;
		long totalCost = 0;
		while (numAdded < n) {
			Pair node = pq.poll();
			int v = node.v;
			if (added[v])
				continue;
			totalCost += node.cost;
			added[v] = true;
			numAdded++;
			for (Pair pair : adj[v])
				if (!added[pair.v])
					pq.add(new Pair(pair.v, pair.cost));
		}
		return totalCost;
	}

	// -------------------------------
	static int[] p;
	static int[] rank;

	public static int kruskal(Edge[] edges) {
		p = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++)
			p[i] = i;
		Arrays.sort(edges);
		int edgesAdded = 0;
		int totalWeight = 0;
		for (int i = 0; i < edges.length && edgesAdded < n - 1; i++) {
			int u = edges[i].from;
			int v = edges[i].to;
			int c = edges[i].weight;
			if (findSet(u) != findSet(v)) {
				union(u, v);
				totalWeight += c;
				edgesAdded++;
			}
		}
		return totalWeight;
	}

	private static int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}

	private static void union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (rank[x] > rank[y])
			p[y] = x;
		else
			p[x] = y;
		if (rank[x] == rank[y])
			rank[y]++;
	}

	public static class Pair implements Comparable<Pair> {
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

	// ---------------------------------------------

	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Edge e) {
			return weight - e.weight;
		}
	}
}
