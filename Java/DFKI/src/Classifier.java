

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Classifier {
	// A map between the values of the attributes and their indices in the 2d array
	// bayes "and attributes too"
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	// 2d array of the values of the attributes
	static String[][] attributes = new String[4][];
	// bayes[i][j][k] is how many times value[j] of attribute [i] occurred when play was k
	// "k is 0 if play=no and 1 if play=yes"
	static int bayes[][][] = new int[4][][];
	// playCount[k] holds how many times play was k
	static int[] playCount = { 0, 0 };
	static {
		// outlook values
		attributes[0] = new String[3];
		attributes[0][0] = "sunny";
		attributes[0][1] = "overcast";
		attributes[0][2] = "rainy";
		// temperature values
		attributes[1] = new String[3];
		attributes[1][0] = "hot";
		attributes[1][1] = "mild";
		attributes[1][2] = "cool";
		// humidity values
		attributes[2] = new String[2];
		attributes[2][0] = "high";
		attributes[2][1] = "normal";
		// windy values
		attributes[3] = new String[2];
		attributes[3][0] = "TRUE";
		attributes[3][1] = "FALSE";

		map.put("no", 0);
		map.put("yes", 1);
		// create index for each value of each attribute
		// since there are no two attributes that have values of the same name,
		// it is safe to use one map for all values, not a map for each
		// attribute.
		for (int i = 0; i < attributes.length; i++)
			for (int j = 0; j < attributes[i].length; j++)
				map.put(attributes[i][j], j);
		for (int i = 0; i < attributes.length; i++)
			bayes[i] = new int[attributes[i].length][2];
	}

	static void train(String outlook, String temp, String hum, String windy,
			String play) {
		String[] in = new String[] { outlook, temp, hum, windy };
		// index will be either 0 or 1
		int index = map.get(play);
		playCount[index]++;
		for (int i = 0; i < in.length; i++) {
			// j=index of in[i] in bayes[i] "index of the value in[i] in the attribute i"
			int j = map.get(in[i]);
			bayes[i][j][index]++;
		}
	}

	static boolean test(String outlook, String temp, String hum, String windy) {
		// playRes[k]= probability that play is k
		double playRes[] = { 1.0, 1.0 };
		//Test for extreme cases "to avoid division by 0", if both values are 0, this means
		//the classifier didn't train yet, by convention I'll regard this as play=yes
		if (playCount[0] == 0)
			return true;
		if (playCount[1] == 0)
			return false;
		String[] in = new String[] { outlook, temp, hum, windy };
		for (int index = 0; index <= 1; index++)
			for (int i = 0; i < attributes.length; i++) {
				int j = map.get(in[i]);
				playRes[index] *= 1.0 * bayes[i][j][index] / playCount[index];
			}
		// To get the real probability we should divide each side by the total
		// number of training samples, but since we are interested only in
		// comparing them, no need to do so, as this won't change the equality
		return playRes[1] * playCount[1] > playRes[0] * playCount[0];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader st = new BufferedReader(
				new FileReader("ClassifierIn.txt"));
		String data;
		String[] ar;
		while ((data = st.readLine()) != null) {
			ar = data.split(",");
			train(ar[0], ar[1], ar[2], ar[3], ar[4]);
		}
		System.out.println(test("sunny", "cool", "high", "TRUE"));
		System.out.println(test("overcast", "cool", "high", "TRUE"));
	}
}
