package main.java.com.excilys.services;

import java.util.ArrayList;

import main.java.com.excilys.models.Computer;
import main.java.com.excilys.models.Page;
import main.java.com.excilys.ui.CommandLineInterface;

public class ComputersList {
	
	// ******** LISTING COMPUTERS METHOD *******
	public static void computersList() {
		CommandLineInterface cli = new CommandLineInterface();
		
		try {
			ArrayList<Computer> computers = cli.getComputerDAO().getAll();
			Page<Computer> pageComputer = new Page<Computer>(computers);
			System.out.println("Size of the computers list :" +  computers.size() + "\n");
			Show.showPage(pageComputer);
		}
		catch(NullPointerException e) {
			System.out.println("Sorry Mr, table \"Computers\" in your Database is empty.\n"
					+ "Please add a computer first.");
			e.printStackTrace();
		}
	}
}