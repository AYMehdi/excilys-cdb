package main.java.com.excilys.services;

import java.sql.Timestamp;
import java.util.Optional;

import com.mysql.cj.util.StringUtils;

import main.java.com.excilys.exceptions.DAOException;
import main.java.com.excilys.exceptions.ItemNotFoundException;
import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.Computer;
import main.java.com.excilys.persistence.ComputerDAO;
import main.java.com.excilys.ui.CommandLineInterface;

public class UpdateComputer {
	
	static CommandLineInterface cli = new CommandLineInterface();
	
	// ******* UPDATING COMPUTER METHOD *******
	public static void updateComputer() throws DAOException, ItemNotFoundException {
		System.out.println("\n***********************");
		System.out.println("** Updating Computer **");
		System.out.println("***********************\n");

		String input = Input.inputComputerID();
		Optional <Computer> computer;

		if (!input.equals("0")) {
			try {
				cli.getComputerDAO();
				computer = ComputerDAO.getById(Integer.parseInt(input));
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

				computer.get().setName(input);
			}

			input = updatingRequest("introduced", computer);

			if (input.equals("Y")) {
				System.out.print(
						"Please enter a new introduction date for your computer or press Enter to make it null (dd/MM/yyyy) : ");
				input = cli.getUserKeyboard().nextLine().trim();

				Timestamp introducedDate = null;

				if (!StringUtils.isNullOrEmpty(input)) {
					introducedDate = Input.datetToTimestamp(input);
				} else {
					computer.get().setDiscontinuedDate(null);
				}
				computer.get().setIntroducedDate(introducedDate);
			}

			if (computer.get().getIntroducedDate() != null) {
				if (computer.get().getDiscontinuedDate() != null
						&& !computer.get().getDiscontinuedDate().after(computer.get().getIntroducedDate())) {
					System.out.println(
							"Sorry Mr, the discontinuation date of your computer is now before its introduction one.\n"
									+ "This is impossible. Please enter a new discontinuation date for your computer (dd/MM/yyyy) : ");
				} else {
					input = updatingRequest("discontinued", computer);
					if (input.equals("Y")) {
						System.out.print(
								"Please enter a new discontinued date for your computer or press Enter to make it null (dd/MM/yyyy) : ");
					}
				}

				if (!input.equals("N")) {
					input = cli.getUserKeyboard().nextLine().trim();

					Timestamp discontinuedDate = null;
					if (!StringUtils.isNullOrEmpty(input)) {
						discontinuedDate = Input.checkDiscontinued(input, computer.get().getIntroducedDate());
					}

					computer.get().setDiscontinuedDate(discontinuedDate);
				}
			}

			input = updatingRequest("company", computer);

			while (!input.equals("Y") && !input.equals("N")) {
				System.out.print("Enter 'Y' to update the manufacturer company of your computer or 'N' to continue : ");
				input = cli.getUserKeyboard().nextLine().trim();
			}
			if (input.equals("Y")) {
				input = Input.inputCompanyID();
				Optional <Company> company = null;
				if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
					try {
						company = cli.getCompanyDAO().getById(Integer.parseInt(input));
					} catch (DAOException e) {
						System.out.println(
								"This company doesn't exist in your Database. The updated manufacturer will be null.\n");
					}
				} else if (input.equals("0")) {
					System.out.println();
					return;
				}

				computer.get().setCompany(company.get());
			}

			cli.getComputerDAO();
			ComputerDAO.update(computer.get());

			System.out.println("\n************************************");
			System.out.println("** Computer updated succesfully ! **");
			System.out.println("************************************\n\n");

		}
	}
	
	// ******* UPDATING REQUEST *******
	public static String updatingRequest(String field, Optional <Computer> computer) {
		StringBuilder update = new StringBuilder("actual_computer \"");

		update.append(field);
		update.append("\" field : ");

		switch (field) {
		case "name":
			update.append(computer.get().getName());
			break;
		case "company":
			try {
				update.append(computer.get().getCompany().getName());
			} catch (NullPointerException e) {
				update.append("unkown Company");
			}
			break;
		case "introducedDate":
			update.append(computer.get().getIntroducedDate());
			break;
		case "discontinuedDate ":
			update.append(computer.get().getDiscontinuedDate());
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