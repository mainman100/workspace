package scheduling;

public class User {
	private String name;
	private int priority;
	Schedule sch;

	public User(String name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
		/*
		 * Initialize the scheduler for this user it is used in fair sharing
		 * only
		 */
		sch = new Schedule(Main.ROUND_ROBIN);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Returns the user with this user name
	 * 
	 * @param userName
	 *            the user Name
	 * @return the user with this user name
	 */
	public static User findUser(String userName) {
		for (int i = 0; i < Main.users.size(); i++)
			if (Main.users.get(i).getName().equals(userName))
				return Main.users.get(i);
		return null;
	}

}
