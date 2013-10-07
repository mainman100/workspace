import java.io.*;
import java.util.Arrays;

public class XO// I really like this program,it is my first
				// game"hope it isn't the last one!"
{
	int x, y;

	public XO(int x, int y) {
		this.x = x;
		this.y = y;
	}// creates an object that store the index of the element

	public static int[][] copy(int[][] a) {
		int[][] b = new int[a.length][a[0].length];
		for (int i = 0; i < a.length; i++)
			b[i] = Arrays.copyOf(a[i], a[i].length);
		return b;
	}

	public static boolean check(int[] a) // Check if the elements of the row are
											// the same
	{
		for (int i = 1; i < a.length; i++) {
			if (a[i] != a[0])
				return false;
		}
		return true;
	}

	public static String binary(int max) {
		String temp = "";
		;
		do {
			temp = max % 2 + temp;
			max /= 2;
		} while (max > 0);
		return temp;
	}// return the binary representation of a decimal number

	public static String binary(int n, int max) {
		String temp = binary(n);
		int diff = binary(max).length() - temp.length();
		for (int i = 1; i <= diff; i++)
			temp = 0 + temp;
		return temp;
	}// read it and you will understand !

	public static void plug(String s, int[][] a) {
		int count = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (a[i][j] != 1 && a[i][j] != 0) {
					a[i][j] = s.charAt(count) - '0';
					count++;
				}
	}// I can't explain it in one line !

	public static boolean Check(int[][] a) // check if the elements of one of
											// the diagonals are the same
	{
		boolean flag1, flag2;
		flag1 = true;
		flag2 = true;
		for (int i = 1; i < a.length; i++) {
			if (a[i][i] != a[0][0])
				flag1 = false;
		}
		int i = a.length - 2, j = 1;
		while (i >= 0) {
			if (a[i][j] != a[a.length - 1][0])
				flag2 = false;
			i--;
			j++;
		}
		return flag1 || flag2;
	}

	public static boolean binaryCheck(String s, int h) {
		int ones = 0, zeros = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				ones++;
			else
				zeros++;
		}
		if (s.length() % 2 == 1) {
			if (ones - zeros == 1 && h % 2 == 0)
				return true;
			return false;
		} else
			return ones == zeros;
	}// Check whether a binary....oh maaaaan!read it!

	public static boolean NoWin(int[][] a, int h) {
		int count = 0;
		int[][] b = copy(a);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (b[i][j] != 1 && b[i][j] != 0)
					count++;
			}
		count = (int) (Math.pow(2, count));
		for (int i = 0; i < count; i++) {
			String temp = binary(i, count - 1);
			if (binaryCheck(temp, h) == true) {
				plug(temp, b);
				if (check(b) == true)
					return false;
				b = copy(a);
			}
		}
		return true;
	}// check whether any of the players can win

	public static void possible_comb(int[][] a, int h)// Display all possible
														// combinations of
														// winning
	{
		int count = 0;
		int[][] b = copy(a);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (b[i][j] != 1 && b[i][j] != 0)
					count++;
			}
		count = (int) (Math.pow(2, count));
		for (int i = 0; i < count; i++) {
			String temp = binary(i, count - 1);
			if (binaryCheck(temp, h) == true) {
				plug(temp, b);
				if (check(b) == true)
					System.out.println(temp);
				b = copy(a);
			}
		}
	}

	public static int[] assign(int[][] a, int x) // Assign the elemnts of a
													// column into an array
	{
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i][x];
		}
		return b;
	}

	public static boolean check(int[][] a) // Chek if one of all above
											// conditions is true
	{
		for (int i = 0; i < a.length; i++) {
			if (check(a[i]) == true)
				return true;
		}
		for (int i = 0; i < a.length; i++) {
			int[] c = assign(a, i);
			if (check(c) == true)
				return true;
		}
		if (Check(a) == true)
			return true;
		return false;
	}

	public static boolean search(int[][] a, int x, int y) // Check if the
															// specified
															// poistion is valid
															// or not
	{
		if (x > a.length - 1 || y > a.length - 1 || x < 0 || y < 0)
			return true;
		else {
			if (a[x][y] == 1 || a[x][y] == 0)
				return true;
			return false;
		}
	}

	public static void display(int[][] a) // Print the current situation
	{
		String s = "";
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (j == 0) {
					if (a[i][j] != 0 && a[i][j] != 1)
						s += "|   |";
					else
						s += "| " + (a[i][j] == 1 ? "X" : "O") + " |";
				} else {
					if (a[i][j] != 0 && a[i][j] != 1)
						s += "   |";
					else
						s += " " + (a[i][j] == 1 ? "X" : "O") + " |";
				}
			}
			if (i != a.length - 1) {
				s += "\n";
				for (int f = 0; f < a.length; f++)
					s += "|___";
				s += "|\n";
			} else {
				s += "\n";
				for (int f = 0; f < a.length; f++)
					s += "|   ";
				s += "|\n";
			}
		}
		System.out.print("\n" + s + "\n");
	}

	public static boolean over(int[][] a)// checks whether the game is over
	{
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i][j] != 1 && a[i][j] != 0)
					return false;
			}
		}
		return true;
	}// check whether the game is over or no

	public static XO set(int[][] a, String s) throws IOException {
		char c = s.charAt(0);
		switch (c) {
		case 'q':
			return new XO(0, 0);
		case 'w':
			return new XO(0, 1);
		case 'e':
			return new XO(0, 2);
		case 'a':
			return new XO(1, 0);
		case 's':
			return new XO(1, 1);
		case 'd':
			return new XO(1, 2);
		case 'z':
			return new XO(2, 0);
		case 'x':
			return new XO(2, 1);
		case 'c':
			return new XO(2, 2);
		default:
			return new XO(3, 3);

		}
	}// The player can enter the position by characters

	public static boolean isDigit(char c) {
		if (c >= '0' && c <= '9')
			return true;
		else
			return false;
	}// Check if the character is a digit

	public static void play() throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Enter the name of player 1");
		String player1 = input.readLine();
		System.out.println("Enter the name of player 2");
		String player2 = input.readLine();
		boolean flag = false;
		;
		do {
			char c1, c2, c3;
			String data;
			int f, b;
			int c = 2;
			int[][] a = new int[3][3];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					a[i][j] = c;
					c++;
				}
			}
			f = 1;
			b = 1;
			int h = 0;
			int count = 0;
			do {
				int errorCount = 0;
				do {
					if (count == 0)
						display(a);
					count++;
					if (errorCount > 0)
						System.out.println("Wrong input !Try again!\n");
					flag = true;
					if (errorCount == 0)
						System.out.println((h % 2 == 0 ? player1 : player2)
								+ "'s turn");
					// System.out.println("All possible combinations of winning are :");
					// possible_comb(a,h);
					errorCount++;
					data = input.readLine();
					if (data.length() == 3) {
						flag = true;
						c1 = '0';
						c2 = '0';
						c3 = '0';
						if (isDigit(data.charAt(0)))
							f = Integer.parseInt(data.charAt(0) + "");
						else
							c1 = '1';
						if (isDigit(data.charAt(2)))
							b = Integer.parseInt(data.charAt(2) + "");
						else
							c2 = '1';
						if (data.charAt(1) != ',' && data.charAt(1) != '-'
								&& data.charAt(1) != ' ')
							c3 = '1';
						if (c1 != '0' || c2 != '0' || c3 != '0')
							flag = false;
					} else if (data.length() == 1) {
						XO t = set(a, data);
						if (t.x == 3)
							flag = false;
						else {
							f = t.x + 1;
							b = t.y + 1;
						}
					} else
						flag = false;
				} while (search(a, f - 1, b - 1) == true || flag == false);
				a[f - 1][b - 1] = (h % 2 == 0 ? 1 : 0);
				Thread.sleep(500);
				display(a);
				h++;
			} while (check(a) == false && over(a) == false
					&& (h > 4 ? NoWin(a, h) == false : true));
			display(a);
			if (NoWin(a, h) == true && over(a) == false)
				System.out.println("No one can win !Match is over!");
			else
				System.out.println((check(a) == true) ? ((h % 2 == 0) ? player2
						: player1) + " wins !" : "No one wins ! ");
			System.out
					.println("Play again? If you want to, enter yes .Else , enter any other thing .");
			data = input.readLine();
			flag = (data.toLowerCase().equals("yes") ? true : false);
		} while (flag == true);
	}// Run the game

	public static void main(String[] args) throws Exception {
		play();
		System.out.println("Thank you and good by.");
	}
}