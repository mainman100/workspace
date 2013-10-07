package Uva;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class AutomateGrades {

	
	static StreamTokenizer st;
	private static int r()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
		st=new StreamTokenizer(new BufferedInputStream(System.in));
		int t=r();
		for(int k=1;k<=t;k++)
		{
			int team1=r();
			int team2=r();
			int finall=r();
			int attend=r();
			int class1=r();
			int class2=r();
			int class3=r();
			double min=Math.min(class1,Math.min(class2,class3));
			double avg=(class1+class2+class3-min)/2.0;
			double grade=team1+team2+finall+attend+avg;
			String res="Case "+k+": ";
			if(grade>=90)
				res+="A";
			else if(grade>=80 &&grade<90)
				res+="B";
			else if (grade>=70 && grade<80)
				res+="C";
			else if (grade>=60 && grade<70)
				res+="D";
			else if (grade<60)
				res+="F";
			System.out.println(res);
		}
	}
}
