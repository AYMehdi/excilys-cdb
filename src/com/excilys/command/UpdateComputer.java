package com.excilys.command;

import java.sql.Timestamp;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOException;
import com.excilys.ui.CommandLineInterface;
import com.mysql.cj.util.StringUtils;

public class UpdateComputer {
	
	static CommandLineInterface cli = new CommandLineInterface();
	
	// ******* UPDATING COMPUTER METHOD *******
	public static void updateComputer() {
		System.out.println("\n***********************");
		System.out.println("** Updating Computer **");
		System.out.println("***********************\n");

		String input = Input.inputComputerID();
		Computer computer;

		if (!input.equals("0")) {
			try {
				computer = cli.getComputerDAO().find(Integer.parseInt(input));
			} catch (DAOException e) {
				System.out.println("Sorry Mr, this computer doesn't exist in your Database.\n");
				return;
			}

			input = updatingRequest("name", computer);

			if (input.equals("Y")) {
				System.out.print("New computer name : ");
				input = cli.getUserKeyboard().nextLine().trim();

				while (StringUtils.isNullOrEmpty(input)) {
					System.out.print(
							"Sorry Mr, the computer must have a name. Please enter a valid name for your computer : ");
					input = cli.getUserKeyboard().nextLine().trim();
				}

				computer.setName(input);
			}

			input = updatingRequest("introducedDate", computer);

			if (input.equals("Y")) {
				System.out.print(
						"Please enter a new introduction date for your computer or press Enter to make it null (dd/MM/yyyy) : ");
				input = cli.getUserKeyboard().nextLine().trim();

				Timestamp introducedDate = null;

				if (!StringUtils.isNullOrEmpty(input)) {
					introducedDate = Input.datetToTimestamp(input);
				} else {
					computer.setDiscontinuedDate(null);
				}
				computer.setIntroducedDate(introducedDate);
			}

			if (computer.getIntroducedDate() != null) {
				if (computer.getDiscontinuedDate() != null
						&& !computer.getDiscontinuedDate().after(computer.getIntroducedDate())) {
					System.out.println(
							"Sorry Mr, the discontinuation date of your computer is now before its introduction one.\n"
									+ "This is impossible. Please enter a new discontinuation date for your computer (dd/MM/yyyy) : ");
				} else {
					input = updatingRequest("discontinuedDate", computer);
					if (input.equals("Y")) {
						System.out.print(
								"Please enter a new discontinued date for your computer or press Enter to make it null (dd/MM/yyyy) : ");
					}
				}

				if (!input.equals("N")) {
					input = cli.getUserKeyboard().nextLine().trim();

					Timestamp discontinuedDate = null;
					if (!StringUtils.isNullOrEmpty(input)) {
						discontinuedDate = Input.checkDiscontinued(input, computer.getIntroducedDate());
					}

					computer.setDiscontinuedDate(discontinuedDate);
				}
			}

			input = updatingRequest("company", computer);

			while (!input.equals("Y") && !input.equals("N")) {
				System.out.print("Enter 'Y' to update the manufacturer company of your computer or 'N' to continue : ");
				input = cli.getUserKeyboard().nextLine().trim();
			}
			if (input.equals("Y")) {
				input = Input.inputCompanyID();
				Company company = null;
				if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
					try {
						company = cli.getCompanyDAO().find(Integer.parseInt(input));
					} catch (DAOException e) {
						System.out.println(
								"This company doesn't exist in your Database. The updated manufacturer will be null.\n");
					}
				} else if (input.equals("0")) {
					System.out.println();
					return;
				}

				computer.setCompany(company);
			}

			cli.getComputerDAO().update(computer);

			System.out.println("\n************************************");
			System.out.println("** Computer updated succesfully ! **");
			System.out.println("************************************\n\n");

		}
	}
	
	// ******* UPDATING REQUEST *******
	public static String updatingRequest(String field, Computer computer) {
		StringBuilder update = new StringBuilder("actual_computer \"");

		update.append(field);
		update.append("\" field : ");

		switch (field) {
		case "name":
			update.append(computer.getName());
			break;
		case "company":
			try {
				update.append(computer.getCompany().getName());
			} catch (NullPointerException e) {
				update.append("unkown Company");
			}
			break;
		case "introducedDate":
			update.append(computer.getIntroducedDate());
			break;
		case "discontinuedDate ":
			update.append(computer.getDiscontinuedDate());
			break;
		}

		update.append(". Do you want to update it ? (Y/N)");
		System.out.print(update);

		String input = cli.getUserKeyboard().nextLine().trim();

		while (!input.equals("Y") && !input.equals("N")) {
			System.out.print("Please enter 'Y' to update your computer \"" + field
					+ "\" field or simply enter 'N' to continue : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		return input;
	}
}