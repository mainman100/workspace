package Spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Alphacode {

	static String msg;
	static long dp[];
	static String[] dic = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26" };


	public static void main(String[] args) throws Exception {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			msg = st.readLine().trim();
			if (msg.equals("0"))
				break;
			dp = new long[msg.length()];
			Arrays.fill(dp,-1);
			for(int i=msg.length()-1;i>=0;i-=1000)
				f(i);
			System.out.println(f(0));
		}
	}

	public static long f(int i){
		if(i == msg.length())
			return 1;
		if(dp [i] ==-1){
			dp [i] = 0L;
			for(String word:dic)
			{
				if(i+word.length() <= msg.length()){
					String sub = msg.substring(i,i+word.length());
					if(sub.equals(word))
						dp [i] += f(i+word.length());
				}
			}
		}
		return dp [i];
	}
}
