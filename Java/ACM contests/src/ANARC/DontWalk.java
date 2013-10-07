package ANARC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DontWalk {

	static int n;
	static int B=1;
	static int W=0;
	public static void main(String[] args) throws IOException {
	//	System.setIn(new FileInputStream("C:\\Users\\Loai_Ghoraba\\Desktop\\hop.in"));
		BufferedReader st=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer out=new StringBuffer();
		for(int t=1;;t++)
		{
			String data=st.readLine();
			if(data.charAt(0)=='-')
				break;
			n=data.length();
			mask m=new mask();
			boolean f=false;
			for(int i=0;i<data.length();i++)
			{
				if(data.charAt(i)=='F')
				{
					m.i=i;
					f=true;
					continue;
				}
				if(data.charAt(i)=='B')
					m.setBit(i-(f?1:0), B);
				else
					m.setBit(i-(f?1:0), W);
			}
			out.append(t+". "+min(m)+"\n");
		}
		System.out.print(out);
	}
	public static int min(mask mas)
	{
		Queue<node> q=new LinkedList<node>();
		HashSet<String> v=new HashSet<String>();
		q.add(new node(new mask(mas), 0));
		boolean[]b={false,true};
		while(!q.isEmpty())
		{
			node e=q.poll();
			int c=e.c;
			mask mask=e.mask;
			if(v.contains(mask.toString()))
				continue;				
			v.add(mask.toString());
			if(mask.ok())
				return c;
			if(c==10)
				continue;
			for(int i=0;i<=1;i++)
				for(int j=0;j<=1;j++)
					if(mask.canMove(b[i], b[j]))
						q.add(new node(mask.move(b[i],b[j]), c+1));
		}
		return -1;
	}
	static class node
	{
		mask mask;
		int c;
		public node(mask mask, int c) {
			super();
			this.mask = mask;
			this.c = c;
		}
		
	}
	static class mask
	{
		long []mask;
		int i;
		public String toString()
		{
			return i+""+mask[0]+""+mask[1];
		}
		public mask()
		{
			mask=new long[2];
		}
		public mask(mask m)
		{
			mask=new long[2];
			mask[0]=m.mask[0];
			mask[1]=m.mask[1];
			i=m.i;
		}
		public int getBit(int i)
		{
			int res=((1L<<(i%64))&mask[i/64])==0?0:1;
			return res;
		}
		public void setBit(int i,int val)
		{
			if(val==1)
				mask[i/64]|=1L<<(i%64);
			else
				mask[i/64]&=~(1L<<(i%64));
		}
		public boolean ok()
		{
			boolean black=false,white=false;
			for(int i=0;i<n-1;i++)
			{
				if(getBit(i)==B)
				{
					if(black&&white)
						return false;
					black=true;
				}
				if(getBit(i)==W&&black)
					white=true;
			}
			return true;
		}
		public boolean canMove(boolean walk,boolean forward)
		{
			if(!walk&&!forward)
			{
				if(i<2)
					return false;
			}
			if(!walk&&forward)
			{
				if(i>n-2)
					return false;
			}
			if(walk&&!forward)
			{
				if(i==0)
					return false;
			}
			if(walk&&forward)
			{
				if(i==n)
					return false;
			}
			return true;
		}
		public mask move(boolean walk,boolean forward)
		{
			mask res=new mask(this);
			if(!walk&&!forward)
			{
				int b1=res.getBit(i-1);
				int b2=res.getBit(i-2);
				res.setBit(i-2, b1);
				res.setBit(i-1,1-b2);
				res.i-=2;
			}
			if(!walk&&forward)
			{
				int b1=res.getBit(i);
				int b2=res.getBit(i+1);
				res.setBit(i, 1-b2);
				res.setBit(i+1,b1);
				res.i+=2;
			}
			if(walk&&!forward)
			{
				res.i--;
			}
			if(walk&&forward)
			{
				res.i++;
			}
			return res;
		}
	}
}
