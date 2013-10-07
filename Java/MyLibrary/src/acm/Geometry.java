package acm;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Geometry {

	public static Point2D[] closestPair(Point2D[] a) {
		Point2D[] res = new Point2D[2];
		Arrays.sort(a, HOR);
		double min = Double.POSITIVE_INFINITY;
		int left = 0;
		SortedSet<Point2D> cand = new TreeSet<Point2D>(VER);
		for (Point2D curr : a) {
			while (curr.getX() - a[left].getX() > min) {
				cand.remove(a[left]);
				left++;
			}
			Point2D up = new Point.Double(curr.getX(), curr.getY() + min);
			Point2D down = new Point.Double(curr.getX(), curr.getY() - min);
			SortedSet<Point2D> toBeExamined = cand.subSet(down, up);
			for (Point2D examine : toBeExamined) {
				double dis = curr.distance(examine);
				if (dis < min) {
					min = dis;
					res[0] = examine;
					res[1] = curr;
				}
			}
			cand.add(curr);
		}
		return res;
	}

	public static final Comparator<Point2D> HOR = new Comparator<Point2D>() {

		@Override
		public int compare(Point2D e1, Point2D e2) {
			if (e1.getX() < e2.getX())
				return -1;
			if (e1.getX() > e2.getX())
				return 1;
			if (e1.getY() < e2.getY())
				return -1;
			if (e1.getY() > e2.getY())
				return 1;
			return 0;
		}
	};
	public static final Comparator<Point2D> VER = new Comparator<Point2D>() {

		@Override
		public int compare(Point2D e1, Point2D e2) {
			if (e1.getY() < e2.getY())
				return -1;
			if (e1.getY() > e2.getY())
				return 1;
			if (e1.getX() < e2.getX())
				return -1;
			if (e1.getX() > e2.getX())
				return 1;
			return 0;
		}
	};
}
