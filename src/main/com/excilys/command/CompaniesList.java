package main.com.excilys.command;

import java.util.ArrayList;

import main .com.excilys.model.Company;
import main .com.excilys.model.Page;
import main.com.excilys.ui.CommandLineInterface;

public class CompaniesList {
	
	// ******** LISTING COMPANIES METHOD *******
	public static void companiesList() {
		CommandLineInterface cli = new CommandLineInterface();
		ArrayList<Company> companies = cli.getCompanyDAO().companiesList();
		Page<Company> pageCompany = new Page<Company>(companies);
		Show.showPage(pageCompany);
	}
}