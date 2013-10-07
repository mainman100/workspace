package Uva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class LightMoreLight {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> squares=new HashSet<Integer>();
		for(int i=1;i<=46340;i++)
			squares.add(i*i);
		while(true)
		{
			int n=Integer.parseInt(st.readLine());
			if(n==0)
				break;
			System.out.println(squares.contains(n)?"yes":"no");
		}
	}
}
