package Spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class UPSUB {

	static String word;
	static ArrayList<Integer>[] s;
	static int q[];
	static ArrayList<Integer>[] prev;
	static Integer[]last;
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(st.readLine());
		StringBuffer out = new StringBuffer();
		while (t-- > 0) {
			word = st.readLine();
			String[] res = res();
			Arrays.sort(res);
			for (String s : res)
				out.append(s+"\n");
			out.append("\n");
		}
		System.out.print(out);
	}
	public static void preProcess()
	{
		s=new ArrayList[word.length()];
		for(int i=0;i<s.length;i++)
			s[i]=new ArrayList<Integer>();
		for(int i=0;i<word.length();i++)
			for(int j=0;j<i;j++)
				if(word.charAt(j)<=word.charAt(i))
					s[i].add(j);
		prev=new ArrayList[word.length()];
		for(int i=0;i<word.length();i++)
			prev[i]=new ArrayList<Integer>();
		q=new int[word.length()];
		Arrays.fill(q,-1);
	}
	public static int f(int i)
	{
		if(q[i]!=-1)
			return q[i];
		int max=0;
		for(int k:s[i])
			max=Math.max(max,f(k));
		for(int k:s[i])
			if(f(k)==max)
				prev[i].add(k);
		return q[i]=max+1;
	}
	public static String[]res()
	{
		preProcess();
		for(int i=0;i<word.length();i++)
			f(i);
		LinkedList<Integer> list=new LinkedList<Integer>();
		int max=0;
		for(int i=0;i<q.length;i++)
			max=Math.max(max, q[i]);
		for(int i=0;i<q.length;i++)
			if(q[i]==max)
			    list.add(i);
		last=list.toArray(new Integer[0]);
		LinkedList<String> out=new LinkedList<String>();
		for(int i=0;i<last.length;i++)
			out.addAll(sol(last[i]));
		return out.toArray(new String[0]);
	}
	public static LinkedList<String> sol(int i)
	{
		LinkedList<String> res=new LinkedList<String>();
		for(int k:prev[i])
		{
			LinkedList<String> temp=sol(k);
			for(int j=0;j<temp.size();j++)
				res.add(temp.get(j)+word.charAt(i));
		}
		if(res.size()==0)
			res.add(word.charAt(i)+"");
		return res;
	}
}
