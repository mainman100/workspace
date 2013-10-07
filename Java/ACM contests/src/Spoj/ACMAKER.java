package Spoj;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class ACMAKER{

	static String[] text;
	static long[][] dp;
	static String abbrev;

	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		StringBuffer out=new StringBuffer();
		while (true) {
			int n = Integer.parseInt(stdin.readLine());
			if (n == 0)
				break;
			String[] insig = new String[n];
			for (int i = 0; i < n; i++)
				insig[i] = stdin.readLine();
			Arrays.sort(insig);
			LinkedList<String> list = new LinkedList<String>();
			while (true) {
				String temp = stdin.readLine();
				if (temp.equals("LAST CASE"))
					break;
				list.add(temp);
			}
			String[] cases = list.toArray(new String[0]);
			for (int i = 0; i < cases.length; i++) {
				String[] split = cases[i].split(" ");
				list = new LinkedList<String>();
				for (int j = 1; j < split.length; j++)
				{
					int index=Arrays.binarySearch(insig,split[j]);
					boolean add=true;
					if(index>=0)
						add=!insig[index].equals(split[j]);
					if (add)
						list.add(split[j]);	
				}

				text = list.toArray(new String[0]);
				abbrev = split[0].toLowerCase();
				dp = new long[abbrev.length()][text.length];
				for (int j = 0; j < dp.length; j++)
					Arrays.fill(dp[j], -1);
				long res = f(0, 0);
				if (res != 0)
					out.append(abbrev.toUpperCase()
							+ " can be formed in " + res + " ways\n");
				else
					out.append(abbrev.toUpperCase()
							+ " is not a valid abbreviation\n");
			}
		}
		System.out.print(out);
	}
	public static int numSubseq(String sub,String phrase)
	{
		int[][]dp=new int[sub.length()][phrase.length()];
		for(int i=0;i<dp.length;i++)
			Arrays.fill(dp[i],-1);
		return dp(0,0,dp,sub,phrase);
	}
	public static int dp(int i,int j,int[][]dp,String sub,String phrase)
	{
		if(i==sub.length())
			return 1;
		if(j==phrase.length())
			return 0;
		if(sub.length()-i>phrase.length()-j)
			return 0;
		if(dp[i][j]!=-1)
			return dp[i][j];
		int count=0;
		if(sub.charAt(i)==phrase.charAt(j))
			count+=dp(i+1, j+1, dp, sub, phrase);
		count+=dp(i, j+1, dp, sub, phrase);
		return dp[i][j]=count;
	}
	public static long f(int i, int j) {
		if (dp[i][j] != -1)
			return dp[i][j];
		String phrase = text[j];
		String s = abbrev.substring(i);
		if (j == text.length - 1) {
			return numSubseq(s, phrase);
		}
		long count = 0;
		int remaining = text.length - j - 1;
		for (int k = 0; k < s.length()
				&& remaining <= abbrev.length() - (i + k + 1); k++) {
			String sub = s.substring(0, k + 1);
			if (sub.length() > phrase.length())
				break;
			int perm=numSubseq(sub, phrase);
			if (perm!=0) {
				long plus = f(i + k + 1, j + 1);
				count += perm*plus;
			}
		}
		return dp[i][j] = count;
	}
}
