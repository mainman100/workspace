import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void f(parent p) {
		System.out.println("parent");
	}

	public static void f(child c) {
		System.out.println("child");
	}

	public static void main(String[] args) {
		parent p = new parent();
		parent p2=new child();
		child c = new child();
		Set<parent> s=new HashSet<parent>();
		f(p);
		f(p2);
		f(c);
	}
}

class parent {

}

class child extends parent {

}