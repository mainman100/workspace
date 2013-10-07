public class Assignment
{
	public static int[] sort(int[] a)
	{
		int max =0;
		for(int i = 0;i<a.length;i++)
		{
			int x = a[i];
			int l=0;
			int n=1;
			
			while(x!=0){
				x =x-x%n;
				n=n*10;
				l++;
			}
			if(max<l)
			max = l;
		}
		System.out.println(max-1);
		System.out.println((8907%10000)/(1000));
		for(int i =0;i<max-1;i++)
		{
			int v;
			int w;
			int u=10;
			for(int j =0;j<max-1-i;j++)
			{
				 w=(a[i]%u)/(u/10);
				 v=(a[i+1]%u)/(u/10);
				if(v<w)
				{
					int temp = a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			u=u*10;
		}
		return a;
	}
	public static void main(String[]args)
	{
		int[] array=new int[7];
		array[0]=133;
		array[1]=555555;
		array[2]=0;
		array[3]=8907;
		array[4]=666;
		array[5]=44444444;
		array[6]=125;
		int[] b =sort(array);
		for(int i=0;i<b.length;i++)
		{
			System.out.println(b[i]);
		} 
		
		//the time complexity is o(n2)because my algorithm depends on bubble sort
		// 125(the last sorted item) is the invariant of my algorithm
	}
}