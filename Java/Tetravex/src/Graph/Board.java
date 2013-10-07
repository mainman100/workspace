package Graph;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
public class Board 
{
	private read scan=new read("");
	private Stack<String> stack=new Stack<String>();
	private Random rand=new Random();
	private square[][]solvedBoard;
	private square[][]mixedBoard;
	private square[][]evaluatedBoard;
	private int size;
	public Board(int n)  
	{
		size=n;
		mixedBoard=new square[n][n];
		evaluatedBoard=new square[n][n];
	    solvedBoard=new square[n][n];
		int r=rand.nextInt(4);
			switch(r)
			{
				case 0:upper_Left_CornerGenerate(n);break;
				case 1:upper_Right_CornerGenerate(n);break;
				case 2:down_Left_CornerGenerate(n);break;
				case 3:down_Right_CornerGenerate(n);break;
			}
		lablabla();
		setposititons();
	}	
	private void upper_Left_CornerGenerate(int n)
	{
		
		size=n;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				mixedBoard[i][j]=new square(size);
				if(j!=0)
					mixedBoard[i][j].setLeft(mixedBoard[i][j-1].getRight());				
				if(i!=0)
					mixedBoard[i][j].setUp(mixedBoard[i-1][j].getDown());	
				while(mixedBoard[i][j].getRight()==mixedBoard[0][0].getLeft())
					mixedBoard[i][j].setRight((int)(Math.random()*10));
				while(mixedBoard[i][j].getDown()==mixedBoard[0][0].getUp())
					mixedBoard[i][j].setDown((int)(Math.random()*10));
				solvedBoard[i][j]=mixedBoard[i][j];
			}
	}
	private void upper_Right_CornerGenerate(int n)
	{
		size=n;
		for(int i=0;i<size;i++)
			for(int j=size-1;j>=0;j--)
			{
				mixedBoard[i][j]=new square(size);
				if(j!=size-1)
					mixedBoard[i][j].setRight(mixedBoard[i][j+1].getLeft());				
				if(i!=0)
					mixedBoard[i][j].setUp(mixedBoard[i-1][j].getDown());	
				while(mixedBoard[i][j].getLeft()==mixedBoard[0][size-1].getRight())
					mixedBoard[i][j].setLeft((int)(Math.random()*10));
				while(mixedBoard[i][j].getDown()==mixedBoard[0][size-1].getUp())
					mixedBoard[i][j].setDown((int)(Math.random()*10));
				solvedBoard[i][j]=mixedBoard[i][j];
			}
	}
	private void down_Left_CornerGenerate(int n)
	{
		size=n;
		for(int i=size-1;i>=0;i--)
			for(int j=0;j<size;j++)
			{
				mixedBoard[i][j]=new square(size);
				if(j!=0)
					mixedBoard[i][j].setLeft(mixedBoard[i][j-1].getRight());				
				if(i!=size-1)
					mixedBoard[i][j].setDown(mixedBoard[i+1][j].getUp());	
				while(mixedBoard[i][j].getRight()==mixedBoard[size-1][0].getLeft())
					mixedBoard[i][j].setRight((int)(Math.random()*10));
				while(mixedBoard[i][j].getUp()==mixedBoard[size-1][0].getDown())
					mixedBoard[i][j].setUp((int)(Math.random()*10));
				solvedBoard[i][j]=mixedBoard[i][j];
			}
	}
	private void down_Right_CornerGenerate(int n)
	{
		size=n;
		for(int i=size-1;i>=0;i--)
			for(int j=size-1;j>=0;j--)
			{
				mixedBoard[i][j]=new square(size);
				if(j!=size-1)
					mixedBoard[i][j].setRight(mixedBoard[i][j+1].getLeft());				
				if(i!=size-1)
					mixedBoard[i][j].setDown(mixedBoard[i+1][j].getUp());	
				while(mixedBoard[i][j].getLeft()==mixedBoard[size-1][size-1].getRight())
					mixedBoard[i][j].setLeft((int)(Math.random()*10));
				while(mixedBoard[i][j].getUp()==mixedBoard[size-1][size-1].getDown())
					mixedBoard[i][j].setUp((int)(Math.random()*10));
				solvedBoard[i][j]=mixedBoard[i][j];
			}
	}			
	private void lablabla()//Mix the tiles
	{
			for(int c=0;c<100;c++)
			{
				int i1=Math.abs(rand.nextInt()%size);
				int j1=Math.abs(rand.nextInt()%size);
				int i2=Math.abs(rand.nextInt()%size);
				int j2=Math.abs(rand.nextInt()%size);
				square temp=mixedBoard[i1][j1];
				mixedBoard[i1][j1]=mixedBoard[i2][j2];
				mixedBoard[i2][j2]=temp;
			}			
		
	}
	private  boolean validPos(square q,int i,int j)
	{
		if (q==null)
			return false;
		if(i>size-1||i<0||j>size-1||j<0)
		{
			return false;	
		}	
		if(evaluatedBoard[i][j]!=null)
			return false;
		if(i!=0)
		{
			if(!(evaluatedBoard[i-1][j]==null||evaluatedBoard[i-1][j]==q))
				if(q.getUp()!=evaluatedBoard[i-1][j].getDown())
					return false;
		}
		if(i!=size-1)
		{
			if(!(evaluatedBoard[i+1][j]==null||evaluatedBoard[i+1][j]==q))
				if(q.getDown()!=evaluatedBoard[i+1][j].getUp())
					return false;			
		}
		if(j!=0)
		{
			if(!(evaluatedBoard[i][j-1]==null||evaluatedBoard[i][j-1]==q))
				if(q.getLeft()!=evaluatedBoard[i][j-1].getRight())
				{
					return false;								
				}
		}
		if(j!=size-1)
		{
			if(!(evaluatedBoard[i][j+1]==null||evaluatedBoard[i][j+1]==q))
				if(q.getRight()!=evaluatedBoard[i][j+1].getLeft())
					return false;			
		}
		return true;
	}
	private boolean move(int board1,int board2,int i1,int j1,int i2,int j2)
	{
		if(board1!=1&&board1!=2||board2!=1&&board2!=2)
			return false;
		try
		{
		if(board1==2)
		{
			if(evaluatedBoard[i1][j1]==null)
			{
					return false;									
			}		
			if(board2==1)
			{
				if(mixedBoard[i2][j2]!=null)
					return false;
				mixedBoard[i2][j2]=evaluatedBoard[i1][j1];
				evaluatedBoard[i1][j1]=null;
				return true;				
			}
			else
			{
				if(board2==2&&validPos(evaluatedBoard[i1][j1],i2,j2))
				{
					evaluatedBoard[i2][j2]=evaluatedBoard[i1][j1];
					evaluatedBoard[i1][j1]=null;
					return true;
				}
				return false;
			}
		}
		else
		{
			if(board2==2)
			{
				if(validPos(mixedBoard[i1][j1],i2,j2))
				{
					evaluatedBoard[i2][j2]=mixedBoard[i1][j1];
					mixedBoard[i1][j1]=null;
					return true;
				}
				return false;				
			}
			else if(board2==1)
			{
				if(mixedBoard[i2][j2]!=null)
					return false;
				else
				{
					mixedBoard[i2][j2]=mixedBoard[i1][j1];
					mixedBoard[i1][j1]=null;
					return true;
				}		
			}
		}			
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}	
	public void setposititons()//Set possible positions for tiles
	{
		boolean flag;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				flag=false;
				for(int i2=0;i2<size&&flag==false;i2++)
					for(int j2=0;j2<size&&flag==false;j2++)
						if(i2!=i||j2!=j)
						{
							if(mixedBoard[i][j].getUp()==mixedBoard[i2][j2].getDown())
							{
								mixedBoard[i][j].mustBeUp(false);
							}
							 if(mixedBoard[i][j].getDown()==mixedBoard[i2][j2].getUp())
							{
								mixedBoard[i][j].mustBeDown(false);
							}
							 if(mixedBoard[i][j].getRight()==mixedBoard[i2][j2].getLeft())
							{
								mixedBoard[i][j].mustBeRight(false);
							}
							 if(mixedBoard[i][j].getLeft()==mixedBoard[i2][j2].getRight())
							{
								mixedBoard[i][j].mustBeLeft(false);
							}
							 flag=mixedBoard[i][j].isUp()==false&&mixedBoard[i][j].isDown()==false&&
							 mixedBoard[i][j].isLeft()==false&&mixedBoard[i][j].isRight()==false;
						}		
			}
	}
	public void solve()
	{
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				mixedBoard[i][j]=solvedBoard[i][j];
			}
	}
	public static String up(square a)
	{
		if(a==null)
			return " ";
		return""+ a.getUp();		
	}
	public static String down(square a)
	{
		if(a==null)
			return " ";
		return""+ a.getDown();			
	}
	public static String right(square a)
	{
		if(a==null)
			return " ";
		return""+ a.getRight();			
	}
	public static String left(square a)
	{
		if(a==null)
			return " ";
		return""+ a.getLeft();			
	}
	public  String dashs()//return the dashs covering the Board1
	{
		String s="";
		for(int i=0;i<7*size;i++)
			s+="-";
		return s;
	}
	public  void display()
	{
		String name="loai";
		square[][]a=mixedBoard;
		square[][]b=evaluatedBoard;
		int n=size;
		name=name.toUpperCase();
		String s="";
		int c=0;
		String num="";
		for(int j=1;j<=n;j++)
			num+="   "+j+"   ";
		num+="        ";
		for(int j=n+1;j<=2*n;j++)
			num+="   "+j+"   ";		
		if(name.length()>n+1)
		{
			int l=name.length()-5;
			for(int i=1;i<=l;i++)
				s+="  "+dashs()+"    "+name.charAt(c++)+" 	 "+dashs()+"\n";
		}
		s+="  "+num+"\n";
		for(int i=0;i<n;i++)
		{ 	
			s+=" "+dashs()+"    "+(c<name.length()?name.charAt(c++):" ")+" 	  "+dashs()+"\n";
			s+=" |";
			for(int j=0;j<n;j++)
				s+="|  "+up(a[i][j])+"  |";
			s+="|		 |";
			for(int j=0;j<n;j++)
				s+="|  "+up(b[i][j])+"  |";	
					s+="|";			
				s+="\n";
			s+=(i+1)+"|";			
			for(int j=0;j<n;j++)
				s+="|"+left(a[i][j])+"   "+right(a[i][j])+"|";
			s+="|		"+(i+1)+"|";
			for(int j=0;j<n;j++)
				s+="|"+left(b[i][j])+"   "+right(b[i][j])+"|";	
				s+="|";					
				s+="\n";
			s+=" |";			
			for(int j=0;j<n;j++)
				s+="|  "+down(a[i][j])+"  |";
			s+="|		 |";
			for(int j=0;j<n;j++)
				s+="|  "+down(b[i][j])+"  |";
				s+="|";	
				s+="\n"	;	
		}
		s+=" "+dashs()+"    "+(c<name.length()?name.charAt(c++):" ")+" 	  "+dashs()+"\n";
		for(int x=c;x<name.length();x++)
			s+=" "+dashs()+"    "+name.charAt(c++)+" 	  "+dashs()+"\n";		
		System.out.println(s);
	}
	public int getsize()
	{
		return size;
	}
	public square[][] getMixedBoard()
	{
		return mixedBoard;
	}
	public square[][] getEvaluatedBoard()
	{
		return evaluatedBoard;
	}
	public square[][] getSolvedBoard()
	{
		return solvedBoard;
	}
	private void undo()
	{
		if(!stack.isEmpty())
		{
			scan.setString(stack.pop());
			int b1=scan.nextInt();
			int b2=scan.nextInt();
			int i1=scan.nextInt();
			int j1=scan.nextInt();
			int i2=scan.nextInt();
			int j2=scan.nextInt();
			move(b1,b2,i1,j1,i2,j2);
		}
	}

}