package com.excilys.command;

import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.model.Page;
import com.excilys.ui.CommandLineInterface;

public class CompaniesList {
	
	// ******** LISTING COMPANIES METHOD *******
	public static void companiesList() {
		CommandLineInterface cli = new CommandLineInterface();
		ArrayList<Company> companies = cli.getCompanyDAO().companiesList();
		Page<Company> pageCompany = new Page<Company>(companies);
		Show.showPage(pageCompany);
	}
}