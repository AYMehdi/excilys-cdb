package main.java.com.excilys.services;

import java.util.ArrayList;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.Page;
import main.java.com.excilys.ui.CommandLineInterface;

public class CompaniesList {
	
	// ******** LISTING COMPANIES METHOD *******
	public static void companiesList() {
		CommandLineInterface cli = new CommandLineInterface();
		
		try {
			ArrayList<Company> companies = cli.getCompanyDAO().getAll();
			Page<Company> pageCompany = new Page<Company>(companies);
			System.out.println("Size of the companies list :" +  companies.size() + "\n");
			Show.showPage(pageCompany);
		}
		catch(NullPointerException e) {
			System.out.println("Sorry Mr, table \"Companies\" in your Database is empty.\n"
					+ "Please add a company first.");
			e.printStackTrace();
		}
	}
}