import java.util.Arrays;
import java.util.HashSet;


public class PRIME {

	public static Integer[] prime(int a,int b)
	{
		HashSet<Integer> res=new HashSet<Integer>();
		for(int i=a;i<=b;i++)
			res.add(i);
		int s=(int)Math.sqrt(a);
		if(s*s!=a)
			s++;
		for(int i=s;i*i<=b;i++)
			if(res.contains(i))
			{
				for(int j=1;j*i<=b;j++)
					res.remove(i*j);				
			}


		Integer[] ar=res.toArray(new Integer[0]);
		Arrays.sort(ar);
		return ar;
	}
}
