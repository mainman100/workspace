package Graph;
import java.util.Random;
public class square
{
	private Random rand=new Random();
	private int up,down,left,right,size=80;
	private boolean selected=false;
	private boolean isRight=false,isLeft=false,isUp=false,isDown=false;
	public square(int size)
	{
		int c;
		size*=size;
		size=999999999;
		//size=10;
		up=rand.nextInt(size);
		c=rand.nextInt(size);
		while(c==up)
			c=rand.nextInt(size);
		left=c;
			c=rand.nextInt(size);
		while(c==up||c==left)
			c=rand.nextInt(size);
		right=rand.nextInt(size);
		c=rand.nextInt(size);
		while(c==right||c==left||c==up)
			c=rand.nextInt(size);
		down=c;		
	}
	public square (int Up,int Down,int Left,int Right)
	{
		up=Up;
		down=Down;
		left=Left;
		right=Right;
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