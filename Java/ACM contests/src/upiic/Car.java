package upiic;

import java.io.*;
import java.util.StringTokenizer;

public class Car {
	static StreamTokenizer st;
	private static double readDouble()throws Exception
	{
 	   st.nextToken();
 	   return  st.nval;
	}
	public static void main(String[] args)throws Exception{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int count=1;
		while(true)
		{
			double a,b;
			int Case = (int)readDouble();
			if(Case==0)
				break;
			double o1=readDouble();
			double o2=readDouble();
			double o3=readDouble();
			if(Case==1){
				a = getA(o1,o2,o3);
				b = getS(o1,o2,a);
				System.out.print("Case " + count + ": ");
				System.out.printf("%.3f",  b);
				System.out.print(" ");
				System.out.printf("%.3f" , a);
				System.out.println();
			}
			else if(Case == 2){
				a = getS(o1,o2,o3);
				b = getT(o1,o2,o3);
				System.out.print("Case " + count + ": ");
				System.out.printf("%.3f",  a);
				System.out.print(" ");
				System.out.printf("%.3f" , b);
				System.out.println();
			}
			else if(Case == 3){
				a = getV(o1,o2,o3);
				b = getT(o1,a,o2);
				System.out.print("Case " + count + ": ");
				System.out.printf("%.3f",  a);
				System.out.print(" ");
				System.out.printf("%.3f" , b);
				System.out.println();
			}
			else{
				a = getU(o1,o2,o3);
				b = getT(a,o1,o2);
				System.out.print("Case " + count + ": ");
				System.out.printf("%.3f",  a);
				System.out.print(" ");
				System.out.printf("%.3f" , b);
				System.out.println();
			}
			count++;
			}		
		}
	
	
	public static Double getA(double o1, double o2, double t){
		return (o2-o1)/t;
	}
	
	public static double getS(double o1, double o2, double a){
		return (Math.pow(o2,2)-Math.pow(o1,2))/(2*a);
	}
	
	public static double getT(double o1, double o2, double o3){
		return (o2-o1)/o3;
	}
	
	public static double getV(double o1, double o2, double o3){
		return Math.sqrt(Math.pow(o1,2)+(2*o2*o3));
	}
	
	public static double getU(double o1, double o2, double o3){
		return Math.sqrt(Math.pow(o1, 2)-(2*o2*o3));
	}

}
