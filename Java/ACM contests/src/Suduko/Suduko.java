package Suduko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Suduko {

	int[][] board;
	boolean[][] row;
	boolean[][] col;
	boolean[][][] squ;
	int n;
	int m;
	boolean solved = false;
	int[][] sol;
	int[][] constraints;

	public void solve(int i, int j) {
		if (solved)
			return;
		if (i == n) {
			solved = true;
			for (int row = 0; row < n; row++)
				for (int col = 0; col < n; col++)
					sol[row][col] = board[row][col] + 1;
			return;
		}
		if (constraints[i][j] != -1)
			solve(i + (j + 1) / n, (j + 1) % n);
		else {
			for (int c = 0; c < n; c++) {
				if (canPlace(i, j, c)) {
					place(i, j, c, true, false);
					solve(i + (j + 1) / n, (j + 1) % n);
					place(i, j, c, false, false);
				}
			}
		}
	}

	public boolean canPlace(int i, int j, int val) {
		return !row[i][val] && !col[j][val] && !squ[i / m][j / m][val];
	}

	public void place(int i, int j, int val, boolean set, boolean cons) {
		row[i][val] = col[j][val] = squ[i / m][j / m][val] = set;
		board[i][j] = val;
		if (cons == true)
			constraints[i][j] = val;
	}
	public boolean put(int i,int j,int val)
	{
		if(canPlace(i, j, val))
		{
			place(i,j,val,true,false);
			return true;
		}
		return false;
	}
	public void unPut(int i,int j,int val)
	{
		place(i, j, val, false, false);
	}
	public Suduko(int side) {
		n = side*side;
		m = side;
		row = new boolean[n][n];
		col = new boolean[n][n];
		squ = new boolean[m][m][n];
		board = new int[n][n];
		sol = new int[n][n];
		constraints = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(constraints[i], -1);
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int side = Integer.parseInt(st.readLine());
		Suduko sud = new Suduko(side);
		int size=side*side;
/*		for(int i=0;i<size;i++)
		{
			StringTokenizer tok = new StringTokenizer(st.readLine());
			for(int j=0;j<size;j++)
			{
				String next=tok.nextToken();
				if(next.equals("-"))
					continue;
				int val=Integer.parseInt(next);
				sud.place(i, j, val - 1, true, true);
			}
		}*/
		sud.solve(0, 0);
		for (int i = 0; i < size; i++)
			System.out.println(Arrays.toString(sud.sol[i]));
	}
}
