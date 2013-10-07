package timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NontrivialNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		String[] data=st.readLine().split(" ");
		int low=Integer.parseInt(data[0]);
		int high =Integer.parseInt(data[1]);
		int div[]=new int[high+1];
		for(int i=1;i<=high;i++)
			for(int j=i;j<=high;j+=i)
				div[j]+=i;
		double min=Double.MAX_VALUE;
		int minNum=0;
		double triv;
		for(int i=low;i<=high;i++)
		{
			triv=1.0*div[i]/i;
			if(triv<min)
			{
				min=triv;
				minNum=i;
			}
		}
		System.out.println(minNum);
	}
}
