package main.com.excilys.command;

import com.mysql.cj.util.StringUtils;

import main .com.excilys.model.Company;
import main.com.excilys.ui.CommandLineInterface;

public class AddCompany {

	static CommandLineInterface cli = new CommandLineInterface();
	
	// ******** ADDING COMPANY METHOD *******
	public static  void addCompany() {
		
		System.out.println("\n********************");
		System.out.println("** Adding Company **");
		System.out.println("********************\n");

		String input = Input.inputCompanyName();
		String name = null;

		if (!input.equals("0")) {
			name = input;
		} else {
			System.out.println();
			return;
		}

		input = Input.inputCompanyID();

		if (!input.equals("0") && !StringUtils.isNullOrEmpty(input)) {
			System.out.println(
					"This company doesn't exist in your Database. "
					+ "The manufacturer will assigned 'null' by default.\n");
		} else if (input.equals("0")) {
			System.out.println();
			return;
		}

		createSQLCompany(name);

		System.out.println("\n*********************************");
		System.out.println("** Company added succesfully ! **");
		System.out.println("*********************************\n\n");
	}

	// ******* ADDING REQUEST *******
	public static void createSQLCompany(String name) {
		Company company = new Company();

		company.setName(name);

		cli.getCompanyDAO().add(company);
	}
}