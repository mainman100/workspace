import java.util.HashMap;
import java.util.Map;

public class Test {

	int x = 2;

	public static void main(String[] args) {
		final Test t = new Test();
		t.x = 3;
		// Collection<E>
		Map<String, Integer> x= getInstance();
	} 

	public static <K, V> HashMap<K, V> getInstance() {
		return new HashMap<K, V>();
	}
}