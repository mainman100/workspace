package solved;
import java.io.BufferedInputStream;
import java.io.StreamTokenizer;


public class Error
{


	static StreamTokenizer st;
	private static int readInt()throws Exception
	{
 	   st.nextToken();
 	   return (int) st.nval;
	}
	public static void main (String[]args)throws Exception
	{
	st=new StreamTokenizer(new BufferedInputStream(System.in));
	StringBuffer out=new StringBuffer();
	int n,cell;
	int[]rowSum;
	int[]colSum;
	int rowCorrupt;
	int colCorrupt;
	boolean corrupt;
	while((n=readInt())!=0)
	{
		rowSum=new int[n];
		colSum=new int[n];
		rowCorrupt=-1;
		colCorrupt=-1;
		corrupt=false;
		for(int i=0;!corrupt&i<n;i++)
		{
			for(int j=0;!corrupt&j<n;j++)
			{
				cell=readInt();
				rowSum[i]+=cell;	
				colSum[j]+=cell;
			}
			if(rowSum[i]%2==1)
			{
				if(rowCorrupt!=-1)
				{
					out.append("Corrupt"+"\n");
					corrupt=true;
				}
				else
					rowCorrupt=i;
			}

		}
		for(int i=0;!corrupt&i<n;i++)
		{
			if(colSum[i]%2==1)
			{
				if(colCorrupt!=-1)
				{
					out.append("Corrupt"+"\n");
					corrupt=true;
				}
				else
					colCorrupt=i;		
			}
		}
		if(!corrupt)
		{
			if(rowCorrupt==-1&&colCorrupt==-1)
				out.append("OK"+"\n");
			else if(rowCorrupt!=-1&&colCorrupt!=-1)
				out.append("Change bit ("+(rowCorrupt+1)+","+(colCorrupt+1)+")"+"\n");
			else
				out.append("Corrupt"+"\n");			
		}
	}
	System.out.print(out);
	}
}
