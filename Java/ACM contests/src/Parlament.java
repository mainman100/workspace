/*import java.io.IOException;
import java.util.Scanner;
public class Parlament {
	static int[]all;
	static StringBuffer out=new StringBuffer();
    public static void main(String[] args) throws IOException{
        Scanner rdr = new Scanner(System.in) ;
        int n = rdr.nextInt() ;
        all = new int[n] ;
        for(int i = 0 ; i < n ; i++)
            all[i] = rdr.nextInt() ;
        construct2(0, n-1);
        String res=out.toString();
        res=res.substring(0,res.length()-1);
        System.out.println(res);
  //      System.out.println();

    }
    public static void construct(int i,int j)
    {
    	int k=j-1;
    	for(;k>=i;k--)
    		if(all[k]<all[j])
    			break;
    	if(k<j-1)
    		construct(k+1, j-1);
    	if(k>=i)
    		construct(i, k);
    	System.out.print(all[j]+" ");
    }
	public static void construct2(int i,int j)
	{
		
		int pos=j-1;
		while(pos>=i&&all[pos]>=all[j])
			pos--;
		if(pos+1<=j-1)
			construct2(pos+1, j-1);
		if(i<=pos)
			construct2(i, pos);
		out.append(all[j]+" ");
	}

}

*/
import java.io.IOException;
import java.util.Scanner;


public class Parlament {

	static int[]b;
	static int n;
	static StringBuffer out;
	public static void main(String[]args) throws NumberFormatException, IOException
	{
		Scanner rdr=new Scanner(System.in);
		n=rdr.nextInt();
		b=new int[n];
		for(int i=0;i<n;i++)
			b[i]=rdr.nextInt();	
		out=new StringBuffer();
		construct(0, n-1);
		String res=out.toString();
		res=res.substring(0,res.length()-1);
		System.out.println(res);
	}
	public static void construct(int i,int j)
	{
		
		int pos=j-1;
		while(pos>=i&&b[pos]>=b[j])
			pos--;
		if(pos+1<=j-1)
			construct(pos+1, j-1);
		if(i<=pos)
			construct(i, pos);
		out.append(b[j]+" ");
	}
}

