package movieTheater;



public class Barrier
{
	private int size;
	private int count=0;
	public Barrier(int size)
	{
		this.size = size;
	}
	public Barrier(int size,boolean turn)
	{
		this.size = size;
	}
	public synchronized void addCurrentThread()
	{
		count++;
		if(count<size)
			goSleep();
		else 
		{
			notifyAll();
			count=0;
		}
	}
	public void goSleep()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getSize()
	{
		return size;
	}
	
}
