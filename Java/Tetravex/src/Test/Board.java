package Test;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
public class Board extends JPanel
{
	private static final long serialVersionUID = -8939174153556335778L;
	private read scan=new read("");
	private Stack<String> stack=new Stack<String>();
	private Random rand=new Random();
	private square[][]solvedBoard;
	private square[][]mixedBoard;
	private square[][]evaluatedBoard;
	private int size;
	private int tileSize;
	private JButton solveButton,newGameButton,undoButton;
	private square selected=null;
	private int elapsedTime;
	private int startTime;
	private JTextField timeLabel;
	private Timer timer;
	private boolean moving=false;
	private int x,y,deltaX,deltaY;
	private Main main;
	public boolean isEmpty()
	{
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				if(mixedBoard[i][j]!=null)
					return false;
		return true;
	}
	public Board(square[][] solved,square[][]mixed,square[][]evaluated,Stack<String> s,int ElapsedTime)//for serializing
	{
		solvedBoard=solved;
		mixedBoard=mixed;
		evaluatedBoard=evaluated;
		setStack(s);
		size=solvedBoard[0].length;
		elapsedTime=ElapsedTime;
		removeSelected();//Removes black borders of selected square,if any
		constructGUI();
		continueTimer();
	}
	public Board(int n,int choise)  
	{
		size=n;
		mixedBoard=new square[n][n];
		evaluatedBoard=new square[n][n];
	    solvedBoard=new square[n][n];
		int r=rand.nextInt(4);
		if(choise==1)
			generate(n);
		else
			switch(r)
			{
				case 0:upper_Left_CornerGenerate(n);break;
				case 1:upper_Right_CornerGenerate(n);break;
				case 2:down_Left_CornerGenerate(n);break;
				case 3:down_Right_CornerGenerate(n);break;
			}
		lablabla();
		constructGUI();
		startTimer();
	}
	public void addMain(Main ma)
	{
		main=ma;
	}
	public void Notify()
	{
		main.Notify();
	}
	private void constructGUI()
	{
		setLayout(null);
		setBackground(Color.BLACK);
		listener listener=new listener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		//*****Add the tiles references to the panel*****
		tileSize=solvedBoard[0][0].getsize();
		setSize(size*tileSize,size*tileSize);
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				add(solvedBoard[i][j]);	
		//***********Configure time label****************
		timeLabel=new JTextField("  "+read.time(elapsedTime));
		timeLabel.setBounds(2*size*tileSize+205,0,50,20);
		timeLabel.setEditable(false);
		add(timeLabel);
		//**********Configure the timer******************
		timer=new Timer(1000,new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int endTime=(int)System.currentTimeMillis()/1000;
				elapsedTime=endTime-startTime;
				//System.out.println("Elapsed Time ="+read.time(elapsedTime));
				timeLabel.setText("  "+read.time(elapsedTime));
			}
		});
		//********Configure solve button***************
		solveButton=new JButton("Solve");
		solveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(selected!=null)
					selected.setSelected(false);
				solve();
				stopTimer();
				repaint();
			}
		});
		solveButton.setFocusPainted(false);
		add(solveButton);
		//***********Configure new game button*********
		newGameButton=new JButton("New game");
		newGameButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(selected!=null)
					selected.setSelected(false);
				final int n=size;
				for(int i=0;i<size;i++)
					for(int j=0;j<size;j++)
					{
						remove(solvedBoard[i][j]);
					}
				int r=rand.nextInt(4);
					switch(r)
					{
						case 0:upper_Left_CornerGenerate(n);break;
						case 1:upper_Right_CornerGenerate(n);break;
						case 2:down_Left_CornerGenerate(n);break;
						case 3:down_Right_CornerGenerate(n);break;
					}
				lablabla();
				for(int i=0;i<size;i++)
					for(int j=0;j<size;j++)
					{
						add(mixedBoard[i][j]);
						evaluatedBoard[i][j]=null;				
					}
				stack=new Stack<String>();
				startTime=(int)System.currentTimeMillis()/1000;
				timeLabel.setText("  "+read.time(0));
				timer.stop();
				timer.start();
				repaint();
			}
		});
		newGameButton.setFocusPainted(false);
		add(newGameButton);
		//********Configure undo button*******************
		undoButton=new JButton("Undo");
		undoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				undo();
			}
		});
		undoButton.setFocusPainted(false);
		add(undoButton);
	}
	private void generate(int n)
	{
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				mixedBoard[i][j]=new square();
				if(j!=0)
					mixedBoard[i][j].setLeft(mixedBoard[i][j-1].getRight());
				if(i!=0)
					mixedBoard[i][j].setUp(mixedBoard[i-1][j].getDown());
				solvedBoard[i][j]=mixedBoard[i][j];
			}		
	}	
	private void upper_Left_CornerGenerate(int n)
	{
		
		size=n;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				mixedBoard[i][j]=new square();
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
				mixedBoard[i][j]=new square();
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
				mixedBoard[i][j]=new square();
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
				mixedBoard[i][j]=new square();
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
	private void solve()
	{
	//	Graph g=new Graph(this);//solving using AI
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				evaluatedBoard[i][j]=solvedBoard[i][j];
				mixedBoard[i][j]=null;
			}
		repaint();
	}
	public int getsize()
	{
		return size;
	}
	public square[][] getSolvedBoard()
	{
		return solvedBoard;
	}
	public square[][] getMixedBoard()
	{
		return mixedBoard;
	}
	public square[][] getEvaluatedBoard()
	{
		return evaluatedBoard;
	}
	public void paintComponent(Graphics g)
	{
		//Graphics2D g2d=(Graphics2D)g;
		//g2d.translate( 50, 50 );
		if(selected!=null)
		{
			this.setComponentZOrder(selected,0);
		}
		int sep=100;
		super.paintComponent(g);
		g.setColor(new Color(238,238,238));
		g.fillRect(0,0,getWidth(),getHeight() );
		g.setColor(Color.BLACK);
		g.drawRect(0,0,tileSize*size,tileSize*size);
		g.drawRect((2*sep+size*tileSize),0,tileSize*size,tileSize*size);
		for(int j=1;j<size;j++)//draw vertical lines
		{
			g.drawLine(j*tileSize,0,j*tileSize,size*tileSize);
			g.drawLine((2*sep+tileSize*size)+j*tileSize,0,(2*sep+tileSize*size)+j*tileSize,size*tileSize);
		}
		for(int i=1;i<size;i++)
		{
			g.drawLine(0,i*tileSize,size*tileSize,i*tileSize);
			g.drawLine(2*sep+size*tileSize,i*tileSize,2*sep+size*2*tileSize,i*tileSize);
		}
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
			{
				if(mixedBoard[i][j]!=null)
					mixedBoard[i][j].setBounds(j*tileSize,i*tileSize,tileSize,tileSize);
				if(evaluatedBoard[i][j]!=null)
					evaluatedBoard[i][j].setBounds(tileSize*size+2*sep+j*tileSize,i*tileSize,tileSize,tileSize);
			}
		solveButton.setBounds(tileSize*size+50,tileSize*size+50,100,50);
		newGameButton.setBounds(tileSize*size+50+110,tileSize*size+50,100,50);
		undoButton.setBounds(tileSize*size+50+50,tileSize*size+110,100,50);
		if(selected!=null&&moving==true)
		{
			selected.setLocation(x-deltaX,y-deltaY);
			selected.repaint();
		}
	}
	private void undo()
	{
		if(!stack.isEmpty()&&!isEmpty())
		{
			scan.setString(stack.pop());
			int b1=scan.nextInt();
			int b2=scan.nextInt();
			int i1=scan.nextInt();
			int j1=scan.nextInt();
			int i2=scan.nextInt();
			int j2=scan.nextInt();
			move(b1,b2,i1,j1,i2,j2);
			repaint();	
		}
	}
	public int getElapsedTime()
	{
		return elapsedTime;
	}
	public void stopTimer()
	{
		timer.stop();
	}
	public void continueTimer()
	{
		startTime=(int)System.currentTimeMillis()/1000-elapsedTime;
		timer.start();
	}
	public void startTimer()
	{
		startTime=(int)System.currentTimeMillis()/1000;
		timer.start();
	}
	public Stack<String> getStack()
	{
		return stack;
	}
	public void setStack(Stack<String> s)
	{
		stack=s;
	}
	private void removeSelected()//remove all borders around tiles
	{
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				solvedBoard[i][j].setSelected(false);	
	}
	private class listener extends MouseAdapter
	{
		private boolean flag;
		private String data="";
		private int x1,y1,x2=-1,y2=-1,b1,b2;
		public void mousePressed(MouseEvent e)
		{
			if(!isEmpty())
			{
				x1=e.getX();
				y1=e.getY()/tileSize;
				deltaY=e.getY()%tileSize;
				if(x1<=size*tileSize)
				{
					x1/=tileSize;
					deltaX=e.getX()%tileSize;
					b1=1;
				}
				else
				{
					if(x1-200-size*tileSize<0)
						x1=-1;
					else
					{
						x1=(x1-200-size*tileSize)/tileSize;
						deltaX=(e.getX()-200-size*tileSize)%tileSize;
					}
					b1=2;
				}
				if(x1>=0&&x1<size&&y1>=0&&y1<size)
				{
					if(!(b1==1&&mixedBoard[y1][x1]==null||b1==2&&evaluatedBoard[y1][x1]==null))
					{
						if(b1==1)
							selected=mixedBoard[y1][x1];
						else
							selected=evaluatedBoard[y1][x1];
						selected.setSelected(true);
						repaint();
					}
				}
			}
		}
		public void mouseDragged(MouseEvent e)
		{
			if(!isEmpty()&&selected!=null)
			{
				moving=true;
				x=e.getX();
				y=e.getY();
				repaint();	
			}
		}
		public void mouseReleased(MouseEvent e)
		{
			if(selected!=null&&!isEmpty())
			{
				int dx=tileSize/2-deltaX;
				int dy=tileSize/2-deltaY;
				moving=false;
				selected.setSelected(false);
				x2=e.getX()+dx;
				y2=(e.getY()+dy)/tileSize;
				if(x2<=size*tileSize)
				{
					x2/=tileSize;
					b2=1;
				}
				else
				{
					if(x2-200-size*tileSize<0)
						x2=-1;
					else
						x2=(x2-200-size*tileSize)/tileSize;
					b2=2;
				}
				if(x2>=0&&x2<size&&y2>=0&&y2<size)
				{
					flag=move(b1,b2,y1,x1,y2,x2);
						if(flag==true)
						{
							data=b2+" "+b1+" "+y2+" "+x2+" "+y1+" "+x1;
							stack.push(data);
						}
				}
				selected=null;
				if(isEmpty()==true)
				{
					timer.stop();					
					Notify();//Notify the main that he solved the board
				}
				repaint();
			}	
		}
	}
}