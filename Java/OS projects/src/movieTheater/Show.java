package movieTheater;

import java.util.Date;


public class Show
{
	private Movie movie;
	/**
	 * Number of tickets booked so far
	 */
	private int spects;
	private Barrier barrier=new Barrier(10);
	private boolean running;
	private Semaphore turn=new Semaphore(0);
        private Semaphore mutex=new Semaphore(1);
	public Show()
	{
	}
	public Show(Movie movie)
	{
		this.movie=movie;
	}
	public void addSpectator()
	{
		barrier.addCurrentThread();
		turn.down(); // Sleep if the movie turn didn't come yet
		mutex.down(); // Gain access to running
		if(running==false)// The first awaked spectator will make the movie run
		{
			running=true;
			Output.print.down();
			Main.out.getStartedArea().append(movie+" started at "+new Date()+" \n");
			Output.print.up();
			mutex.up();
			movie.run();
		}
		else 
			mutex.up();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void incremeantSpects()
	{
		spects++;
	}
	public void decremeantSpects()
	{
		spects--;
	}
	public Movie getMovie()
	{
		return movie;
	}
	public int getSpects()
	{
		return spects;
	}
	public void runMovie()
	{
		movie.run();
	}
	/**
	 * @param isTurn whether it is this movie turn or not
	 */
	public void setTurn(boolean isTurn) {
		if(isTurn==true)
		{
			for(int i=0;i<10;i++)
				turn.up();	
		}
	}
}
