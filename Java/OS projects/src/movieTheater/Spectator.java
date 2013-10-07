package movieTheater;

import java.util.ArrayList;
import java.util.Date;


public class Spectator extends Thread 
{
	private int index;
	private int movieNum;
	private static int count=1;
	public Spectator(int movie)
	{
		this.movieNum=movie;
		start();
	}
	public void run()
	{
                Main.th.mutex.down();
		Date arrived=new Date();
		Output.print.down();
		index=count++;
		Main.out.getArrivedArea().append(this+"arrived at "+arrived+"\n");
		Output.print.up();
		ArrayList<Movie> movies=Theater.getMovies();
		Movie movie=movies.get(movieNum);
		Output.print.down();
                Main.out.getChosedArea().append(this+ " chosed "+movie+"at "+new Date()+" \n");
		Output.print.up();
		Show show=bookTicket(movie);
                Main.th.mutex.up();
                if(show!=null)
                     watchMovie(show);
                Main.th.mutex.down();
                leaveTheater(show==null);
                Main.th.mutex.up();
	}
	public Show bookTicket(Movie movie)
	{
		return Main.th.addMovieRequest(movie);
	}
	public void watchMovie(Show show)
	{
		show.addSpectator();
	}
	public void leaveTheater(boolean thrown)
	{
                if(thrown==false)
                    Main.th.spectLeaved();
              	Output.print.down();
                if(thrown==true)
                    Main.out.getThrownArea().append(this+" was throwen out the theater at "+ new Date()+"\n");
                else
                    Main.out.getLeavedArea().append(this+" leaved the theater at "+ new Date()+"\n");

		Output.print.up();
	}
	public String toString()
	{
		return "Spectator "+index+" ";
	}
}
