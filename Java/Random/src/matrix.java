import java.io.*;
public class matrix
{
	double[][]array;
	public matrix(int l,int w,int x)throws IOException
	{
		if(x==1)
		{
		BufferedReader data=new BufferedReader (new InputStreamReader(System.in));
		array=new double[l][w];
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<w;j++)
			{
				System.out.println("Enter the element in position ("+(i+1)+","+(j+1)+")");
				array[i][j]=Double.parseDouble(data.readLine());
			}
		
		}
    	}
    	else
    	{
    		array=new double[l][w];
    	}
	}
	public matrix copy()throws IOException
	{
		matrix c=new matrix(array.length,array[0].length,0);
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				
				c.array[i][j]=array[i][j];
			}
		}
		return c;
	}
	public double det()throws IOException
	{
		if(array.length==1)
			return array[0][0];
		else
		{
			int x=array[0].length;
			matrix[]co=new matrix[x];
			for(int i=0;i<x;i++)
			{
				co[i]=new matrix(x-1,x-1,0);
			}
			for(int i=0;i<x;i++)
			{
				for(int j=1;j<x;j++)
				{
					int newCount=0,arrayCount=0;
					while(arrayCount<x)
					{
						if(i!=arrayCount)
						{
						co[i].array[j-1][newCount]=array[j][arrayCount];
						newCount++;
						}
						arrayCount++;
					}
				}
			}
			double  sum=0;
			for(int i=0;i<x;i++)
			{
				sum+=array[0][i]*Math.pow(-1,i)*co[i].det();	
			}
			return sum;
		}
	}
	public double defdet(int a,int b)throws IOException
	{
		int icount=0,jcount,i=0,j=0,l=array.length;
		matrix c=new matrix(l-1,l-1,0);
		while(i<l)
		{
			jcount=0;
			j=0;
			while(j<l)
			{	
				if(i!=a)
				{
					if(j!=b)
					{
					c.array[icount][jcount]=array[i][j];
					jcount++;
					}
				}
				j++;
			}
			if(i!=a)
				icount++;
			i++;
		}
		double sum=c.det();
		return sum;
		
	}
	public matrix mid()throws IOException
	{
		int l=array.length;
		matrix c=new matrix(l,l,0);
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<l;j++)
			{
				c.array[i][j]=Math.pow(-1,i+j)*defdet(i,j);
			}
		}
		return c;
	}
	public matrix transpose()throws IOException
	{
		int l=array.length;
		int m=array[0].length;
		matrix c=new matrix(m,l,0);
		for(int i=0;i<l;i++)
		{
			for(int j=0;j<m;j++)
			{
				c.array[i][j]=array[j][i];
			}
		}
		return c;
	}
	public void devide(double x)
	{
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			array[i][j]/=x;
		}
	}
	public matrix inverse()throws IOException
	{
			matrix b=this.mid();
			b=b.transpose();
			b.devide(this.det());
			return b;	
	}
	public matrix(double[][]a)
	{
		array=a;
	}
	public matrix multiply(matrix a)throws IOException
	{
		matrix A=this.copy();
		matrix B=a.copy();
		matrix C=new matrix(array.length,B.array[0].length,0);
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				double sum=0;
				for(int k=0;k<B.array.length;k++)
				{
					sum+=A.array[i][k]*B.array[k][j];
				}
				C.array[i][j]=sum;
			}
		}
		return C;
	}
	public String toString()
	{
		int i,j;
		String data="|";
		for(i=0;i<array.length;i++)
		{
			for(j=0;j<array[0].length;j++)
			{
					int y=(int)array[i][j];	
						if(array[i][j]==y)
							data+=y;
						else
							data+=array[i][j];

						data+="\t";
			}
			if(i==array.length-1)
				data+="|\n";
			else
				data+="|\n|";
		}	
		return data;
		
	}
	public static matrix add(matrix a,matrix b)throws IOException
	{
		matrix d=new matrix (a.array.length,a.array[0].length,0);
		for(int i=0;i<a.array.length;i++)
		{
			for(int j=0;j<a.array[0].length;j++)
			{
				d.array[i][j]=a.array[i][j]+b.array[i][j];
			}
		}
		
		return d;
	}
	public static boolean compare(matrix a,matrix b)throws IOException
	{
		for(int i=0;i<a.array.length;i++)
		{
			for(int j=0;j<a.array[i].length;j++)
			{
				if(a.array[i][j]!=b.array[i][j])
					return false;
			}
		}
		return true;
	}
	public static boolean adding(matrix a, matrix b,matrix c)throws IOException
	{
		if(!(compare(add(a,b),c)))
			return false;
		if(!(compare(add(c,b),a)))
			return false;
		if(!(compare(add(a,c),b)))
			return false;
		return true;	
	}
	public static void main(String[]args)throws IOException
	{
		matrix a=new matrix (3,3,1);
		System.out.println(a);
	}
	}