package acm;

public class DisjointSets {

	int[] p;
	int[] rank;

	public DisjointSets(int size) {
		p = new int[size];
		rank = new int[size];
	}

	public void makeSet(int x) {
		p[x] = x;
		rank[x] = 0;// no need to because it is already 0
	}

	public int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}

	public void union(int x, int y) {
		link(findSet(x), findSet(y));
	}

	private void link(int x, int y) {
		if (rank[x] > rank[y])
			p[y] = x;
		else
			p[x] = y;
		if (rank[x] == rank[y])
			rank[y]++;
	}
}
