package scheduling;

import java.util.LinkedList;

public class Main {

	public static LinkedList<User> users = new LinkedList<User>();

	// Defining constants that represents the algorithms
	public static final int SHORTEST_FIRST = 0;
	public static final int MULTI_LEVEL = 1;
	public static final int LOTTERY = 2;
	public static final int FAIR_SHARING = 3;
	public static final int ROUND_ROBIN = 4;

	public static final int unitTime = 1;
	/**
	 * A global variable that represents the current time
	 */
	public static int currentTime = 0;

	public static void main(String[] args) throws InterruptedException {

		setUsers();

		int algorithm = 0;
		// un comment the line you want to get the algorithm you want

		// algorithm=SHORTEST_FIRST;
		// algorithm=MULTI_LEVEL;
		// algorithm=LOTTERY;
		algorithm = FAIR_SHARING;

		Process p1 = new Process("Process 1", 1, 3, "Loai");
		Process p2 = new Process("Process 2", 2, 7, "Loai");
		Process p3 = new Process("Process 3", 3, 4, "Loai");
		Process p4 = new Process("Process 4", 4, 2, "Hashem");
		Process p5 = new Process("Process 5", 5, 1, "Hashem");

		if (algorithm == FAIR_SHARING) {
			FairQueue sch = new FairQueue();
			sch.addProcess(p1);
			sch.addProcess(p2);
			sch.addProcess(p3);
			sch.addProcess(p4);
			sch.addProcess(p5);
			sch.run();
		} else {
			Schedule sch = new Schedule(algorithm);
			sch.addProcess(p1);
			sch.addProcess(p2);
			sch.addProcess(p3);
			sch.addProcess(p4);
			sch.addProcess(p5);
			sch.run();
		}

	}

	/**
	 * Instantiate a list of users
	 */
	private static void setUsers() {
		User user1 = new User("Loai", 2);
		User user2 = new User("Ali", 2);
		User user3 = new User("Hashem", 1);
		User user4 = new User("Hussain", 1);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
	}
}
