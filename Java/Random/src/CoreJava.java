import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class CoreJava {

	public static Pair get() {
		return new Pair();
	}

	public static void main(String[] args) throws CloneNotSupportedException {

		CoreJava.Pair p = get();
		ArrayList<Integer> x;
		LinkedList<Integer> y = new LinkedList<>();
		Object z = y.iterator();
		y.removeAll(null);
		Arrays.asList(null);
		boolean g = y.contains(null);
		Map<Integer, String> map = new HashMap<Integer, String>();
		Set<Integer> set=map.keySet();
		AbstractList<Integer> sd;
	//	ss
	}

	public static class Pair {

		int x, y;

	}
}
