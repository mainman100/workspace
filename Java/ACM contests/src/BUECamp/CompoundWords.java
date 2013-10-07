package BUECamp;
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


public class CompoundWords {

//	static ArrayList<String>dic=new ArrayList<String>();
	static HashMap<Integer, String> dic=new HashMap<Integer, String>();
	public static void main(String[]args) throws IOException
	{
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			String word=st.readLine();
			if(word==null||word.length()==0)
				break;
			dic.put(word.length(),word);
		}
		LinkedList<String> res=new LinkedList<String>();
		for(int i:dic.keySet())
		{
			int[]len=dic.get(i);
		}
		String[]ar=res.toArray(new String[0]);
		Arrays.sort(ar);
		StringBuffer out=new StringBuffer();
		for(String w:ar)
			out.append(w+"\n");
		System.out.print(out);
	}
}
*/