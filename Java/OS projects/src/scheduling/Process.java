package scheduling;

import static scheduling.Main.*;

public class Process implements Comparable<Process> {
	private String name;
	private int arrivalTime;
	private int duration;
	private User user;
	private int remainingTime;
	/**
	 * The level of the queue this process is currently in it is used only in
	 * Multilevel feed back scheduling
	 */
	private int level = 1;
	/**
	 * The type of algorithm used with this process,Multilevel ,fair sharing,
	 * etc..
	 */
	private int algorithm;
	/**
	 * Indicates the turn of the process, used in Fair sharing to ensure Round
	 * Robin scheduling in each user, if process A turn variable > Process B
	 * turn variable then A's last run was after B's last run thus B will be
	 * scheduled to run before A
	 * */

	private int turn = 1;
	/**
	 * A shared variable that is incremented after each process finishes its
	 * quantum in Round Robin, and then its value is assigned the process turn
	 * variable.
	 */
	private static int lastTurn = -1;
	/**
	 * A boolean variable that indicates whether this process hasn't completed
	 * its quantum (it is used in Round Robin algorithm only) because the
	 * scheduler of this process has finished the allowed time for it to run,
	 * according to the priority of the user. so we need to keep track of this
	 * info so that in the next run we run this process first and run it for
	 * only one time slot, not two
	 */
	private boolean broken;

	public Process(String name, int arrivalTime, int duration, String userName) {
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		// Initially the remaining time is equal to the run time
		remainingTime = duration;
		// Find the user of which this process belong
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getName().equals(userName))
				this.user = users.get(i);
		// Set the turn variable for this process
		turn = arrivalTime;
		/*
		 * Just as when all processes turn value is set, we need to know which
		 * is larger to initialize the lastTurn variable, as it holds the
		 * largest turn so far
		 */
		if (arrivalTime > lastTurn)
			lastTurn = arrivalTime + 1;

	}

	/**
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the remainingTime
	 */
	public int getRemainingTime() {
		return remainingTime;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param algorithm
	 *            the algorithm to set
	 */
	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @param level
	 *            the level to increment
	 */
	public void incrementLevel() {
		// If the level is 4, no change
		if (level != 4)
			level++;
	}

	public void decremeantRemainingTime(int dec) {
		remainingTime -= dec;
	}

	@Override
	/**
	 * Just overriding compareTo method, it compare
	 * the processes according to the type of algorithm
	 * thus allowing it to be put in PQ and to get the next
	 * process to run, we just call pq.poll()
	 * 
	 */
	public int compareTo(Process p) {
		/*
		 * If the algorithm is round robin, then the only thing I care about is
		 * the turn variable, whoever has a smaller value will run before those
		 * who have a larger one
		 */
		if (algorithm == ROUND_ROBIN)
			return turn - p.turn;
		/*
		 * If the algorithm is lottery, the processes are put into PQ until its
		 * arrival time come and then added to the tickets linked list so we
		 * need to sort them only according to the arrival time
		 */
		if (algorithm == LOTTERY)
			return arrivalTime - p.arrivalTime;
		/*
		 * If the algorithm is SRTN or MLFB, so whatever time remains or the
		 * level it is in, if the arrival time of this process hasn't come yet,
		 * it will be put in the end of the PQ
		 */
		if (arrivalTime > currentTime || p.arrivalTime > currentTime)
			return arrivalTime - p.arrivalTime;
		if (algorithm == SHORTEST_FIRST)// Shortest remaining-time next
		{
			/*
			 * If the remaining time differs, chose the smaller otherwise if
			 * they are both equal , chose who arrived first
			 */
			int diff = remainingTime - p.remainingTime;
			if (diff != 0)
				return diff;
		}
		if (algorithm == MULTI_LEVEL) // MultiLevel
		{
			/*
			 * If the levels differs, chose the smaller otherwise if they are
			 * both equal , chose who arrived first
			 */
			int diff = level - p.level;
			if (diff != 0)
				return diff;
		}
		/*
		 * If they are both equal in terms of the metric
		 * "remaining time or the level" then chose the one that arrived first
		 */
		return arrivalTime - p.arrivalTime;

	}

	public String toString() {
		return name;
	}

	/**
	 * Assigns the turn variable to lastTurn+1, so when it is called on a
	 * process, this process has the largest value for turn variable now
	 */

	public void incremeantTurn() {
		turn = lastTurn++;
	}

	public int getTurn() {
		return turn;
	}

	/**
	 * @return whether this process was stopped before consuming its quantum
	 *         ,that is it has one more unit time to run before it finishes its
	 *         quantum
	 */
	public boolean isBroken() {
		return broken;
	}

	/**
	 * @param broken
	 *            sets whether this process was broken
	 */
	public void setBroken(boolean broken) {
		this.broken = broken;
	}

}
