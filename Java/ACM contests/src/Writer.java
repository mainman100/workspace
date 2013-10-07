import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	public void write() throws IOException {
		{
			BufferedWriter wr = new BufferedWriter(new FileWriter(
					"C:\\Users\\Loai_Ghoraba\\Desktop\\ACM\\in.txt"));
			wr.write("300000\n");
			for(int i=1;i<=300000;i++)
				wr.write(1000000+"\n");
			wr.close();
		}
	}

	public static void main(String[] args) throws IOException {
		Writer wr = new Writer();
		wr.write();
	}
}
