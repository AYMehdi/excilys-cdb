package main.java.com.excilys.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.mysql.cj.util.StringUtils;

import main.java.com.excilys.ui.CommandLineInterface;

public class Input {
	static CommandLineInterface cli = new CommandLineInterface();
	
	// ******* COMPUTER *******
	public static String inputComputerID() {
		System.out.print("Please enter an ID for your computer. "
				+ "You can enter '0' to come back to the menu : ");
		String input = cli.getUserKeyboard().nextLine().trim();

		while ((StringUtils.isNullOrEmpty(input) || !StringUtils.isStrictlyNumeric(input)) && !input.equals("0")) {
			System.out.print(
					"Invalid computer ID entry. Please enter a valid ID for your computer. "
					+ "You can enter '0' to come back to the menu : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		return input;
	}
	
	public static String inputComputerName() {
		System.out.print("Please enter a name for your computer. "
				+ "You can enter '0' and to back to the menu : ");
		String input = cli.getUserKeyboard().nextLine().trim();

		while (StringUtils.isNullOrEmpty(input) && !input.equals("0")) {
			System.out.print(
					"Sorry Mr, the name of your computer cannot be null. "
					+ "Please enter a valid name. You can enter '0' to come back to the menu : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		return input;
	}
	
	public static Timestamp datetToTimestamp(String inputDate) {
		Timestamp dateSQL = null;

		try {
			Date date = cli.getDateFormat().parse(inputDate);
			long time = date.getTime();
			dateSQL = new Timestamp(time);
			return dateSQL;
		} catch (ParseException e) {
			System.out.println(
					"Sorry Mr, the format of your date is incorrect because it does not respect "
					+ "the format'dd/MM/yyyy'. This date will be null by default.\n");
			return dateSQL;
		}
	}
	
	public static Timestamp checkDiscontinued(String inputDate, Timestamp introducedDate) {
		Timestamp discontinuedDate = datetToTimestamp(inputDate);

		if (discontinuedDate.before(introducedDate)) {
			System.out.println(
					"Sorry Mr, the discontinuation date of your computer must be after introduction one. "
					+ "Discontinuation date will be null by default.\n");
			discontinuedDate = null;
		}

		return discontinuedDate;
	}
	
	// ******* COMPANY *******
	public static String inputCompanyID() {
		System.out.print(
				"Please enter an ID for this manufacturer company. "
				+ "You can press 'Enter' if it's unknown (the default value will be null) "
				+ "or '0' to exit and come back to the menu : ");
		String input = cli.getUserKeyboard().nextLine().trim();

		while (!StringUtils.isStrictlyNumeric(input) && !StringUtils.isNullOrEmpty(input) && !input.equals("0")) {
			System.out.print(
					"Invalid company ID entry. Please enter a valid ID for your manufacturer company. "
					+ "You can enter '0' to come back to the menu : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		return input;
	}
	
	public static String inputCompanyName() {
		System.out.print("Please enter a name for your company. "
				+ "You can enter '0' and to back to the menu : ");
		String input = cli.getUserKeyboard().nextLine().trim();

		while (StringUtils.isNullOrEmpty(input) && !input.equals("0")) {
			System.out.print(
					"Sorry Mr, the name of your company cannot be null. "
					+ "Please enter a valid name. You can enter '0' to come back to the menu : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		return input;
	}
}