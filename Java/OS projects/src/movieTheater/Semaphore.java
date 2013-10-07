package movieTheater;


public class Semaphore 
{
	private int count;

	public Semaphore(int count)
	{
		this.count=count;
	}
	
	public synchronized void up()
	{
		count++;
		if(count<=0)
			notify();
	}
	public synchronized void down()
	{
		count--;
		if(count<0)
			goSleep();		
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
}
