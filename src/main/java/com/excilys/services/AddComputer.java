package main.java.com.excilys.services;

import java.sql.Timestamp;
import java.util.Optional;

import com.mysql.cj.util.StringUtils;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.Computer;
import main.java.com.excilys.ui.CommandLineInterface;

public class AddComputer {

	static CommandLineInterface cli = new CommandLineInterface();
	
	// ******** ADDING COMPUTER METHOD *******
	public static  void addComputer() {
		System.out.println("\n*********************");
		System.out.println("** Adding Computer **");
		System.out.println("*********************\n");

		String input = Input.inputComputerName();
		String name = null;

		if (!input.equals("0")) {
			name = input;
		} else {
			System.out.println();
			return;
		}

		System.out.print(
				"Enter the introduction date of the computer (dd/MM/yyyy). "
				+ "You can enter '0' to cancel the add and come back to the menu : ");
		input = cli.getUserKeyboard().nextLine().trim();
		Timestamp introducedDate = null;

		if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
			introducedDate = Input.datetToTimestamp(input);
		} else if (input.equals("0")) {
			System.out.println();
			return;
		}

		System.out.print(
				"Enter the discontinuedDate date of the computer (dd/MM/yyyy). "
				+ "You can press '0' to cancel the add : ");
		Timestamp discontinuedDate = null;

		if (introducedDate != null) {
			input = cli.getUserKeyboard().nextLine().trim();
			if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
				discontinuedDate = Input.checkDiscontinued(input, introducedDate);
			} else if (input.equals("0")) {
				System.out.println();
				return;
			}
		}

		input = Input.inputCompanyID();
		Optional <Company> company = null;

		if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
			try {
				company = cli.getCompanyDAO().get(Integer.parseInt(input));
			} catch (DAOException e) {
				System.out.println(
						"This company doesn't exist in your Database. "
						+ "The manufacturer will assigned 'null' by default.\n");
			}
		} else if (input.equals("0")) {
			System.out.println();
			return;
		}

		createSQLComputer(name, company.get(), introducedDate, discontinuedDate);

		System.out.println("\n**********************************");
		System.out.println("** Computer added succesfully ! **");
		System.out.println("**********************************\n\n");
	}

	// ******* ADDING REQUEST *******
	public static void createSQLComputer(String name, Company company, Timestamp introducedDate, Timestamp discontinuedDate) {
		Computer computer = new Computer();

		computer.setName(name);
		computer.setCompany(company);
		computer.setIntroducedDate(introducedDate);
		computer.setDiscontinuedDate(discontinuedDate);

		cli.getComputerDAO().add(computer);
	}
}