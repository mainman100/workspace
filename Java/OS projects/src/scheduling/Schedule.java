package scheduling;

import java.util.LinkedList;
import java.util.PriorityQueue;

import static scheduling.Main.*;

public class Schedule {
	/**
	 * PQ that holds processes until they finish their run time. If the
	 * algorithm is lottery, then the PQ is used to hold processes until their
	 * arrival time come and then they are popped and added to the tickets list
	 * "declared below"
	 */
	private PriorityQueue<Process> queue = new PriorityQueue<Process>();
	/**
	 * Current running process
	 */
	private Process currentProcess;
	/**
	 * Type of algorithm
	 */
	private int algorithm;
	/**
	 * The time this schedule can run in Fair Sharing algorithm, it depends on
	 * the priority of the user, it gets decremented after each process run,
	 * when its value is zero, the schedule stops and another user's schedule
	 * begin running. it is abbreviation for Round Robin Time
	 */
	private int rrTime;
	/**
	 * Represents the user this schedule belongs to, it is used in round robin
	 * only
	 */
	public User user;
	/**
	 * The list of tickets, it is used in Lottery scheduling only.
	 */
	private LinkedList<Process> tickets = new LinkedList<Process>();

	/**
	 * Initiates the schedule with this algorithm
	 * 
	 * @param algorithm
	 *            the algorithm type to set
	 */
	public Schedule(int algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * Runs the schedule, it is the core method of schedule class it has a
	 * return type that is used only in Fair sharing, as the schedule may be
	 * stopped because its assigned time slots are finished, but its processes
	 * may not have finished yet, so when it returns true to indicate that all
	 * processes have finished running or false otherwise
	 * 
	 * @return true if all processes have finished running or false otherwise
	 * @throws InterruptedException
	 */
	public boolean run() throws InterruptedException {
		/*
		 * Initialize the time slots allowed to this schedule to run if it is
		 * using round robin to mange fair sharing
		 */
		if (algorithm == ROUND_ROBIN)
			rrTime = user.getPriority() * 2;
		while (!finished()) {
			currentProcess = getNextProcess();
			// If no process's arrival time has came yet
			if (currentProcess == null) {
				// If it is fair sharing, switch to another user schedule
				if (algorithm == ROUND_ROBIN)
					return false;
				// Print a message indicating that no process is running
				printTimeTrace(0);
			} else {
				// Get the run time for this process
				int runTime = getRunTime();
				// Print the time trace for this process
				printTimeTrace(runTime);
				/*
				 * Clean up and update needed things after this process finishes
				 * its assigned run time
				 */
				finalizeCurrentProcess(runTime);
				// If the algorithm is round robin, this needs special handling
				if (algorithm == ROUND_ROBIN) {
					/*
					 * If the time slots allowed for this user's schedule are
					 * finished then switch to another controller
					 */
					if (rrTime == 0) {
						/*
						 * If the run time was odd, so this process was taken
						 * off from its quantum, so we will not increment the
						 * turn variable so that it will be scheduled to run in
						 * the next time
						 */
						if (runTime % 2 == 1)
							currentProcess.setBroken(true);
						else
							currentProcess.incremeantTurn();
						return finished();
					}
					/*
					 * Increment the turn variable of this process so as to put
					 * it at the end of the queue
					 */
					currentProcess.incremeantTurn();
				}
			}
		}
		/*
		 * When the processes are all finished , return true
		 * "the return value is used only in fair sharing"
		 */
		return true;
	}

	/**
	 * Returns the time allowed for this process to run continuously without
	 * interrupts (i.e. it will run this time completely and than check for
	 * whatever interrupts, e.g. arrival of new processes with less remaining
	 * time, lower level..)
	 */
	private int getRunTime() {
		/*
		 * If the algorithm is shortest first, then it will run one unit time
		 * and then check for arrival of new processes
		 */
		if (algorithm == SHORTEST_FIRST)
			return unitTime;
		/*
		 * If algorithm is multilevel , return the minimum of (allowed quantum
		 * time in the current process's level) and (the remaining time for this
		 * process)
		 */
		if (algorithm == MULTI_LEVEL)
			return Math.min((int) Math.pow(2, currentProcess.getLevel() - 1),
					currentProcess.getRemainingTime());
		/*
		 * If algorithm is lottery , return the minimum of (quantum time) and
		 * (the remaining time for this process)
		 */
		if (algorithm == LOTTERY)
			return Math.min(2 * unitTime, currentProcess.getRemainingTime());
		/*
		 * If algorithm is round robin , return the minimum of (allowed quantum
		 * time in the current process's level) and (the remaining time for this
		 * process) and (the remaining allowed time for this user schedule to
		 * run)
		 */
		if (algorithm == ROUND_ROBIN) {
			if (currentProcess.isBroken()) {
				currentProcess.setBroken(false);
				return unitTime;
			}
			return Math.min(rrTime, Math.min(2 * unitTime, currentProcess
					.getRemainingTime()));
		}

		return 0;
	}

	/**
	 * Adds a process to the PQ
	 * 
	 * @param p
	 *            the process to add
	 */
	public void addProcess(Process p) {
		p.setAlgorithm(algorithm);
		queue.add(p);
	}

	/**
	 * Returns the next process to be scheduled to run, depending on the type of
	 * the algorithm
	 * 
	 * @return the next process to run
	 */
	private Process getNextProcess() {
		// Refresh queue to detect new arrival processes
		refreshQueue();
		if (algorithm == LOTTERY) {
			// Get all arrived processes, pop them off and assign them tickets
			while (queue.size() > 0
					&& queue.peek().getArrivalTime() <= currentTime)
				setTickets(queue.poll());
			// If no process is running now, return null
			if (tickets.size() == 0)
				return null;
			// Pick a ticket, hope you will win =D
			int ticket = (int) (Math.random() * tickets.size());
			// Return the winning process
			return tickets.get(ticket);
		}
		/*
		 * If the highest priority process arrival time didn't come yet, return
		 * null
		 */
		Process p = queue.peek();
		if (p.getArrivalTime() > currentTime)
			return null;
		// Return the process
		return queue.poll();
	}

	/**
	 * Clean up and update needed things after this process finishes its
	 * assigned run time
	 * 
	 * @param runTime
	 *            the time the last process ran
	 */
	private void finalizeCurrentProcess(int runTime) {
		// Decrement the time the last process has ran
		currentProcess.decremeantRemainingTime(runTime);

		if (algorithm == LOTTERY) {
			// If the algorithm is lottery and the process has
			// finished, just remove its tickets
			if (currentProcess.getRemainingTime() == 0)
				removeTickets(currentProcess);
		}
		// If the process didn't finish yet
		else if (currentProcess.getRemainingTime() != 0) {
			if (algorithm == MULTI_LEVEL)
				currentProcess.incrementLevel();
			// Add the process again to the queue
			queue.add(currentProcess);
		}
	}

	/**
	 * Returns whether the schedule has finished running all its processes
	 */
	private boolean finished() {
		/*
		 * If it is lottery, return either if both the size of the queue and the
		 * tickets is 0
		 */

		if (algorithm == LOTTERY)
			return Math.max(queue.size(), tickets.size()) == 0;
		return queue.size() == 0;
	}

	/**
	 * Called to check for processes that their arriving time has came, as the
	 * queue doesn't sort them again automatically
	 */
	private void refreshQueue() {
		Process[] pr = queue.toArray(new Process[0]);
		queue = new PriorityQueue<Process>();
		for (int i = 0; i < pr.length; i++)
			queue.add(pr[i]);
	}

	/**
	 * Prints the time trace, i.e. which process is running when. If
	 * <code>runTime</code> =0, then no process ran
	 * 
	 * @param runTime
	 *            the time the current process has ran
	 */
	private void printTimeTrace(int runTime) {
		if (runTime == 0) {
			System.out.println("No process is running at time " + currentTime);
			try {
				Thread.sleep(unitTime * 500);
				currentTime++;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		for (int i = runTime; i > 0; i--) {
			try {
				System.out.println(currentProcess + " is running at time "
						+ currentTime);
				Thread.sleep(unitTime * 500);
				currentTime++;
				/*
				 * Decrement the remaining time for this schedule , this will
				 * not have any effect on the schedule unless the algorithm is
				 * round robin
				 */
				rrTime--;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	/**
	 * Give tickets to process p, called only when the process arrival time
	 * comes
	 */
	private void setTickets(Process p) {
		User user = p.getUser();
		int numTickets = user.getPriority();
		for (int i = 0; i < numTickets; i++)
			tickets.add(p);// Give it the tickets
	}

	/**
	 * Remove tickets from process p, called only when the process has finished
	 */
	private void removeTickets(Process p) {
		/*
		 * Remove all instances of process p from the tickets list, if an
		 * instance is removed, the remove() method returns true so the loop
		 * will continue , if there is no instance, the remove() method returns
		 * false and the loop terminates
		 */
		while (tickets.remove(p)) {

		}
	}

	/**
	 * Sets the allowed time this schedule can run in case of round robin, which
	 * is used in fair sharing
	 * 
	 * @param rrTime
	 *            the time allowed for this schedule to run
	 */
	public void setRoundRopinTime(int rrTime) {
		this.rrTime = rrTime;
	}

	/**
	 * Returns whether there are processes that can run in this schedule, that
	 * is if there are processes whose arrival time has come, called only in
	 * round robin
	 * 
	 * @return is there are processes capable of running
	 */
	public boolean hasAvailableProcess() {
		return queue.peek().getArrivalTime() <= currentTime;
	}
}
