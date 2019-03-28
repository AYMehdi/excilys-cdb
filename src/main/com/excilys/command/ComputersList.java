package main.com.excilys.command;

import java.util.ArrayList;

import main .com.excilys.model.Computer;
import main .com.excilys.model.Page;
import main.com.excilys.ui.CommandLineInterface;

public class ComputersList {
	
	// ******** LISTING COMPUTERS METHOD *******
	public static void computersList() {
		CommandLineInterface cli = new CommandLineInterface();
		
		try {
			ArrayList<Computer> computers = cli.getComputerDAO().computersList();
			Page<Computer> pageComputer = new Page<Computer>(computers);
			Show.showPage(pageComputer);
		}
		catch(NullPointerException e) {
			System.out.println("Sorry Mr, table \"Computers\" in your Database is empty.\n"
					+ "Please add a computer first.");
		}
	}
}