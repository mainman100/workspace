package movieTheater;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;


public class Theater 
{
	private static ArrayList<Movie> movies=new ArrayList<Movie>();
	private int moviesPlayed=0;
	public static LinkedList<Show> queue=new LinkedList<Show>();
	private Show dummy=new Show();
	private Show nextShow;
	private Show lastShow=null;
	public  Semaphore mutex=new Semaphore(1);

	public Theater()
	{
		for(int i=1;i<=20;i++)
			movies.add(new Movie("Movie "+i,i));
	}
	
	/**
	 * @return the movies
	 */
	public static ArrayList<Movie> getMovies() {
		return movies;
	}
	/**
	 * Returns the number of movies played today so far. To ensure that 10
	 * shows are played at maximum
	 * @return the moviesPlayed
	 */
	public int getMoviesPlayed() 
	{
		return moviesPlayed;
	}
	/**
	 * Adds a movie to the queue of the movies to be played and returns 
	 * the movie edition representing it 
	 * @param movie the movie to add
	 * @return the <code>show</code> representing it
	 */
	public Show addMovieRequest(Movie movie)
	{
		//See if you can just use a current requested movie
		for(int i=0;i<queue.size();i++)
		{
			Show show=queue.get(i);
			if(show.getMovie()==movie)
				if(show.getSpects()<10)
				{
					show.incremeantSpects();
					if((nextShow==null||nextShow==dummy)&&i==0&&show.getSpects()==10)//It is this show tuen 
					{
						nextShow=show;
						show.setTurn(true);
					}
					return show;
				}
		}
		
		//See if you can fill a gap because of two consecutive same movies
		Show show=new Show(movie);
		for(int i=0;i<queue.size();i++)
		{
			Show temp=queue.get(i);
			if(temp==dummy)
				if(queue.get(i+1).getMovie()!=show.getMovie())// No two consecutive movies !
				{
					queue.set(i,show);
					show.incremeantSpects();
					moviesPlayed++;
					return show;
				}
		}
                // If this didn't work and we have already 10 movies played-->rawwa7
                if(moviesPlayed==10)
                    return null;
		//Just add a new movie request in the end of the queue
		try // If the queue is not empty
		{
			Show last=queue.getLast();
			if(show.getMovie()==last.getMovie())
				queue.addLast(dummy);
		}
		catch(NoSuchElementException e)//Empty queue
		{
			//What if the last one played was the same ?
			if(lastShow!=null)
			{
				if(lastShow.getMovie()==show.getMovie())
					queue.addLast(dummy);
			}
		}
		queue.addLast(show);
		show.incremeantSpects();
		moviesPlayed++;	
		return show;
	}
	public void spectLeaved()
	{
		nextShow.decremeantSpects();
		if(nextShow.getSpects()==0)// All body moved
		{
			try
			{
				lastShow=nextShow;
				queue.removeFirst();
				nextShow=queue.getFirst();
				nextShow.setTurn(true);
			}
			catch (NoSuchElementException e)// Empty list
			{
				nextShow=null;
			}

		}
	}
}
