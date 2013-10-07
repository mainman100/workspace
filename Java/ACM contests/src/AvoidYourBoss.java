import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AvoidYourBoss {
	static int[][] map;
	static boolean[] visited;
	static int P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String in = br.readLine();
			if (in == null)
				break;
			StringTokenizer st = new StringTokenizer(in);
			P = Integer.parseInt(st.nextToken());
			map = new int[P][P];
			visited = new boolean[P];
			int r = Integer.parseInt(st.nextToken());
			int BH = Integer.parseInt(st.nextToken()) - 1;
			int OF = Integer.parseInt(st.nextToken()) - 1;
			int YH = Integer.parseInt(st.nextToken()) - 1;
			int M = Integer.parseInt(st.nextToken()) - 1;
			int t1, t2, t3;
			for (int h = 0; h < r; h++) {
				st = new StringTokenizer(br.readLine());
				t1 = Integer.parseInt(st.nextToken()) - 1;
				t2 = Integer.parseInt(st.nextToken()) - 1;
				t3 = Integer.parseInt(st.nextToken());
				map[t1][t2] = t3;
				map[t2][t1] = t3;
			}
			String tmo = DijkstraBoss(BH, OF);
			System.out.println(tmo);
			st = new StringTokenizer(tmo);
			while (st.hasMoreTokens())
				visited[Integer.parseInt(st.nextToken())] = true;
			int s = dijkstra(YH, M);
			if (s == -1)
				System.out.println("MISSION IMPOSSIBLE.");
			else
				System.out.println(s);
		}
	}

	public static int dijkstra(int s, int e) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(s, 0, "" + s + " "));
		while (!pq.isEmpty()) {
			Node current = pq.remove();
			if (visited[current.i])
				continue;
			visited[current.i] = true;
			if (current.i == e)
				return current.c;
			for (int i = 0; i < P; i++)
				if (map[current.i][i] != 0 && !visited[i])
					pq.add(new Node(i, current.c + map[current.i][i], current.v
							+ i + " "));
		}
		return -1;
	}

	public static String DijkstraBoss(int s, int e) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean[] v = new boolean[P];
		pq.add(new Node(s, 0, "" + s + " "));
		while (!pq.isEmpty()) {
			Node current = pq.remove();
			if (v[current.i])
				continue;
			v[current.i] = true;
			if (current.i == e) {
				String r = current.v + " ";
				int t = current.c;
				while (!pq.isEmpty()) {
					current = pq.remove();
					if (current.i == e && current.c == t)
						r += current.v + " ";
					if (current.c > t)
						break;
				}
				return r;
			}
			for (int i = 0; i < P; i++)
				if (map[current.i][i] != 0 && !v[i])
					pq.add(new Node(i, current.c + map[current.i][i], current.v
							+ i + " "));
		}
		return "";
	}
}

class Node implements Comparable<Node> {
	int i;
	int c;
	String v;

	Node(int x, int y, String s) {
		i = x;
		c = y;
		v = s;
	}

	public int compareTo(Node e) {
		return c - e.c;
	}
}
