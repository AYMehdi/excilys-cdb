package main.com.excilys.command;

import main .com.excilys.model.Computer;
import main.com.excilys.persistence.DAOException;
import main.com.excilys.ui.CommandLineInterface;

public class ShowComputers {
	
	// ******* PRINCIPAL METHOD *******
	public static void showComputer() {
		CommandLineInterface cli = new CommandLineInterface();
		
		String input = Input.inputComputerID();

		if (!input.equals("0")) {
			try {
				Computer computer = cli.getComputerDAO().find(Integer.parseInt(input));
				System.out.println(computer.toString());
			} catch (DAOException e) {
				System.out.println("Sorry Mr, this computer doesn't exist in your Database.\n");
			}
		}
	}
}