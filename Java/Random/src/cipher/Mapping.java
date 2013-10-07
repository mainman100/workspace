package cipher;

import java.io.File;
import java.io.IOException;
import static file.FileOperations.*;

public class Mapping {
	public static String encode(String text, String key) {
		String temp = "";
		for (int i = 0; i < text.length(); i++)
			temp += key.charAt(i % key.length());
		String encoded = "";
		for (int i = 0; i < text.length(); i++) {
			int old = (int) text.charAt(i);
			int func = (int) temp.charAt(i);
			int mapped = (old - func + 128) % 128;
			char enc = (char) mapped;
			encoded += enc;
		}
		return encoded;
	}

	public static String decode(String text, String key) {
		String temp = "";
		for (int i = 0; i < text.length(); i++)
			temp += key.charAt(i % key.length());
		String encoded = "";
		for (int i = 0; i < text.length(); i++) {
			int old = (int) text.charAt(i);
			int func = (int) temp.charAt(i);
			int mapped = (old + func + 128) % 128;
			char enc = (char) mapped;
			encoded += enc;
		}
		return encoded;
	}

	public static void encode(File input, File output, String key)
			throws IOException, ClassNotFoundException {
		String original = readFromFile(input);
		String cyphered = encode(original, key);
		writeToFile(output, cyphered);
	}

	public static void decode(File input, File output, String key)
			throws IOException, ClassNotFoundException {
		String cyphered = readFromFile(input);
		String original = decode(cyphered, key);
		writeToFile(output, original);
	}
}
