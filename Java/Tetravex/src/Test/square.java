package Test;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
public class square extends JPanel implements Serializable
{
	private static final long serialVersionUID = -6475444873129044372L;
	private transient Random rand=new Random();
	private int up,down,left,right,size=80;
	private boolean selected=false,visited=false;
	private boolean isRight=true,isLeft=true,isUp=true,isDown=true;
	public square()
	{
		int c;
		up=rand.nextInt(10);
		c=rand.nextInt(10);
		while(c==up)
			c=rand.nextInt(10);
		left=c;
			c=rand.nextInt(10);
		while(c==up||c==left)
			c=rand.nextInt(10);
		right=rand.nextInt(10);
		c=rand.nextInt(10);
		while(c==right||c==left||c==up)
			c=rand.nextInt(10);
		down=c;		
		setLayout(null);
		setSize(80,80);
	}
	public square (int Up,int Down,int Left,int Right)
	{
		up=Up;
		down=Down;
		left=Left;
		right=Right;
		setSize(size,size);
	}
	public void paintComponent(Graphics g)
	{
		int n=size;
		g.setColor(getColor(up));
		int xValues1[]={0,n,n/2};
		int yValues1[]={0,0,n/2};
		g.fillPolygon(xValues1,yValues1,3);
		g.setColor(getColor(left));
		int[]xValues2={0,n/2,0};
		int[]yValues2={0,n/2,n};
		g.fillPolygon(xValues2,yValues2,3);
		g.setColor(getColor(down));
		int[]xValues3={n/2,0,n};
		int[]yValues3={n/2,n,n};
		g.fillPolygon(xValues3,yValues3,3);
		g.setColor(getColor(right));
		int[]xValues4={n,n/2,n};
		int[]yValues4={0,n/2,n};
		g.fillPolygon(xValues4,yValues4,3);
		g.setColor(Color.BLACK);
		g.setFont( new Font( "Serif", Font.BOLD, 20 ) );
		g.drawString(""+up,n/2-3,n/5);
		g.drawString(""+down,n/2-5,4*n/5+9);
		g.drawString(""+left,n/8,n/2+3);
		g.drawString(""+right,7*n/8-3,n/2+3);
		g.drawRect(0,0,n,n);
		g.drawLine(0,0,n,n);
		g.drawLine(n,0,0,n);
		if(selected==true)
		{
			Graphics2D g2d = ( Graphics2D ) g;
			 g2d.setPaint( Color.BLACK );                            
			 g2d.setStroke( new BasicStroke( 4.0f ) );            
			 g2d.draw( new Rectangle2D.Double(0,0,n,n));
		}
	}
	private Color getColor(int x)
	{
		switch(x)
		{
			case 0:return Color.BLUE;
			case 1:return Color.YELLOW;
			case 2:return Color.GREEN;
			case 3:return Color.PINK;
			case 4:return Color.MAGENTA;
			case 5:return Color.ORANGE;
			case 6:return Color.GRAY;
			case 7:return Color.CYAN;
			case 8:return Color.WHITE;
			case 9:return new Color(150,100,180);
		}
		return Color.BLACK;
	}
	public String toString()
	{
		return "up "+up+" left "+left+" down "+down+" right "+right;
	}
	public int getsize()
	{
		return size;
	}
	public int getUp()
	{
		return up;
	}
		public int getDown()
	{
		return down;
	}
		public int getRight()
	{
		return right;
	}
		public int getLeft()
	{
		return left;
	}
	public void setUp(int n)
	{
		up=n;
	}
	public void setDown(int n)
	{
		down=n;
	}
	public void setRight(int n)
	{
		right=n;
	}
	public void setLeft(int n)
	{
		left=n;
	}
	public void setSelected(boolean b)
	{
		selected=b;
	}
	public boolean getSelected()
	{
		return selected;
	}
	public void mustBeUp(boolean b)
	{
		isUp=b;
	}
	public void mustBeDown(boolean b)
	{
		isDown=b;
	}
	public void mustBeLeft(boolean b)
	{
		isLeft=b;
	}
	public void mustBeRight(boolean b)
	{
		isRight=b;
	}
	public boolean isUp()
	{
		return isUp;
	}
	public boolean isDown()
	{
		return isDown;
	}
	public boolean isLeft()
	{
		return isLeft;
	}
	public boolean isRight()
	{
		return isRight;
	}
	public boolean isUpperLeftCorner()
	{
		return isLeft&&isUp;
	}
	public boolean isUpperRightCorner()
	{
		return isRight&&isUp;
	}
	public boolean isLowerLeftCorner()
	{
		return isLeft&&isDown;
	}
	public boolean isLowerrightCorner()
	{
		return isRight&&isDown;
	}
	public square clone()
	{
		return new square(up,down,left,right);
	}
}