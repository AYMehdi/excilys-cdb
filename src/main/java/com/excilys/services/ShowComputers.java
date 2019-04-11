package main.java.com.excilys.services;

import java.util.Optional;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.model.Computer;
import main.java.com.excilys.ui.CommandLineInterface;

public class ShowComputers {
	
	// ******* PRINCIPAL METHOD *******
	public static void showComputer() {
		CommandLineInterface cli = new CommandLineInterface();
		
		String input = Input.inputComputerID();

		if (!input.equals("0")) {
			try {
				Optional <Computer> computer = cli.getComputerDAO().get(Integer.parseInt(input));
				System.out.println(computer.toString());
			} catch (DAOException e) {
				System.out.println("Sorry Mr, this computer doesn't exist in your Database.\n");
			}
		}
	}
}