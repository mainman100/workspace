package dateFinding.Engine;

import javax.swing.JFrame;

import dateFinding.GUI.InputPanel;

public class Date {
	private int day = -1, month = -1, year = -1;

	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public boolean check()// Check whether the Date exist
	{
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return day >= 1 && day <= 31;
		} else if (month == 2) {
			if (year % 4 != 0)
				return day >= 1 && day <= 28;
			else
				return day >= 1 && day <= 29;
		} else if (month == 4 || month == 6 || month == 9 || month == 11)
			return day >= 1 && day <= 30;
		else
			return false;
	}

	public int Months_days_diff()// Number of days from the beginning of the
									// specified
									// year until the day in the specified year
	{
		int diff = 0;
		switch (month) {
		case 12:
			diff += 30;
		case 11:
			diff += 31;
		case 10:
			diff += 30;
		case 9:
			diff += 31;
		case 8:
			diff += 31;
		case 7:
			diff += 30;
		case 6:
			diff += 31;
		case 5:
			diff += 30;
		case 4:
			diff += 31;
		case 3:
			diff += 28;
		case 2:
			diff += 31;
		case 1:
			diff += 0;
		}
		if (month > 2)
			diff += (year % 4 != 0 ? 0 : 1);
		diff += day;
		return diff;
	}

	public int All_diff()// calculate the number of days
	{
		int diff = 0;
		if (year > 2008) {
			int n = year - 2008;
			int x = n - n % 4;
			diff += (int) (0.75 * x * 365 + (x / 4) * 366 + (n % 4 != 0 ? (((n % 4) - 1) * 365 + 366)
					: 0));
			diff += Months_days_diff();
		} else {
			int n = 2008 - year;
			int x = n - n % 4;
			diff += (int) (0.75 * x * 365 + (x / 4) * 366 + n % 4 * 365);
			diff -= Months_days_diff();
			diff *= -1;
		}
		return diff;
	}

	public String getDate() {
		if (!check())
			return "No such date exist!!";
		int diff = All_diff();
		diff %= 7;
		if (diff < 0)
			diff += 7;
		String day = "";
		switch (diff) {
		case 1:
			day = "Tuesday";
			break;
		case 2:
			day = "Wednesday";
			break;
		case 3:
			day = "Thursday";
			break;
		case 4:
			day = "Friday";
			break;
		case 5:
			day = "Saturday";
			break;
		case 6:
			day = "Sunday";
			break;
		case 0:
			day = "Monday";
			break;
		}
		return day;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame(
				"Enter the date and get the corresponding day");
		frame.setContentPane(new InputPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}