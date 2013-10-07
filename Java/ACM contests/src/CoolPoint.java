import java.io.BufferedInputStream;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class CoolPoint {

	static StreamTokenizer st;

	private static int r() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	private static double getAngle(double x, double y) {
		if (x == 0) {
			if (y >= 0)
				return 90;
			else
				return 270;
		}
		double absAng = Math.atan(Math.abs(y / x)) * (180 / Math.PI);
		if (x >= 0 && y >= 0)
			return absAng;
		if (x <= 0 && y >= 0)
			return 180 - absAng;
		if (x <= 0 && y <= 0)
			return 180 + absAng;
		if (x >= 0 && y <= 0)
			return 360 - absAng;
		return 0;
	}

	private static pair getPair(double x, double y) {
		double ang1 = Math.min(x, y);
		double ang2 = Math.max(x, y);
		if (ang2 - ang1 > 180) {
			double temp = ang1;
			ang1 = ang2;
			ang2 = temp + 360;
		}
		return new pair(ang1, ang2);
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		st = new StreamTokenizer(new BufferedInputStream(System.in));
		int t, n;
		t = r();
		for (int i = 0; i < t; i++) {
			n = r();
			int radius = r();
			if (n == 0) {
				if (radius == 0)
					System.out.println("Case " + (i + 1) + ": 0.00%");
				else
					System.out.println("Case " + (i + 1) + ": 100.00%");
			} else {
				ArrayList<pair> pairs = new ArrayList<pair>();
				for (int j = 0; j < n; j++) {
					int x1 = r(), y1 = r(), x2 = r(), y2 = r();
					double ang1 = 0, ang2 = 0;
					if (y1 * y2 < 0) {
						double slope = 0, xIntersection = 0;
						try {
							slope = (y2 - y1) * 1.0 / (x2 - x1);
							xIntersection = (-1 * y1 / slope) + x1;
						} catch (ArithmeticException e) {
							// This means that x1=x2 so the intersection points
							// will have the same sign as x1 and x2
							xIntersection = x1;
						}
						double hor = xIntersection > 0 ? 0 : 180;
						// First line
						ang2 = getAngle(x1, y1);
						pairs.add(getPair(hor, ang2));
						// Second line
						ang2 = getAngle(x2, y2);
						pairs.add(getPair(hor, ang2));
					} else {
						ang1 = getAngle(x1, y1);
						ang2 = getAngle(x2, y2);
						pairs.add(getPair(ang1, ang2));
					}
				}
				pair[] arr = pairs.toArray(new pair[0]);
				LinkedList<pair> res = new LinkedList<pair>();
				pair[] set = new pair[arr.length * 2];
				for (int j = 0; j < arr.length; j++) {
					set[2 * j] = new pair(arr[j].ang1, 1);
					set[2 * j + 1] = new pair(arr[j].ang2, -1);
				}
				Arrays.sort(set);
				double start = set[0].ang1;
				double count = 1;
				for (int j = 1; j < set.length; j++) {
					if (count == 0)
						start = set[j].ang1;
					count += set[j].ang2;
					if (count == 0)
						res.add(new pair(start, set[j].ang1));
				}
				pair[] result = res.toArray(new pair[0]);
				double sum = 0;
				for (pair p : result)
					sum += p.ang2 - p.ang1;
				System.out.print("Case " + (i + 1) + ": ");
				System.out.printf("%.2f", 100 * ((360 - sum) / 360));
				System.out.println("%");
			}
		}
	}
}

class pair implements Comparable<pair> {
	double ang1;
	double ang2;

	pair(double x, double y) {
		ang1 = x;
		ang2 = y;
	}

	@Override
	public int compareTo(pair p) {
		if (ang1 < p.ang1)
			return -1;
		if (ang1 > p.ang1)
			return 1;
		return 0;
	}
}
/*
 * 
 * 
 * 3 4 1000 -5 -81 -45 27 27 -91 36 4 -21 18 -16 95 -69 67 -12 -99 1 10 2 0 0 2
 * 0 5
 */