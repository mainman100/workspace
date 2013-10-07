package scheduling;

import static scheduling.Main.currentTime;
import static scheduling.Main.unitTime;

import java.util.LinkedList;

public class FairQueue {
	/**
	 * The list of schedules for each user
	 */
	LinkedList<Schedule> schs = new LinkedList<Schedule>();

	/**
	 * Adds a process to the correct schedule, that is to the schedule of the
	 * user it belongs to
	 * 
	 * @param p
	 *            the process to add
	 */
	public void addProcess(Process p) {
		Schedule sch = p.getUser().sch;
		sch.addProcess(p);
		sch.user = p.getUser();
		/*
		 * If this is the first time for us to use add a process of this user
		 * then add his schedule to the list of schedules
		 */

		if (!schs.contains(sch))
			schs.add(sch);
	}

	/**
	 * Do the job :D
	 * 
	 * @throws InterruptedException
	 */
	public void run() throws InterruptedException {
		// While there are still schedules not finished yet
		while (schs.size() > 0) {
			// flag to test whether there is a schedule capable of running now
			boolean allNotReady = true;
			// Traverse all schedules
			for (int i = 0; i < schs.size() && allNotReady; i++)
				/*
				 * If this schedule is capable of running some processes,then
				 * change the flag
				 */
				if (schs.get(i).hasAvailableProcess())
					allNotReady = false;
			// If no schedule can run any process, print this
			if (allNotReady == true) {
				System.out.println("No process is running at time "
						+ currentTime);
				try {
					Thread.sleep(unitTime * 500);
					currentTime++;
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				// Get the first schedule in the list
				Schedule sch = schs.removeFirst();
				/*
				 * Run the schedule , check whether it has finished all its
				 * Process or not
				 */
				boolean finished = sch.run();
				/*
				 * If it hasn't finished yet, add it to the end of the list, so
				 * that it will be able to run again when its turn come
				 */
				if (!finished)
					schs.addLast(sch);
			}
		}
	}

}
