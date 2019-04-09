package main.java.com.excilys.services;

import com.mysql.cj.util.StringUtils;

import main.java.com.excilys.exception.DAOException;
import main.java.com.excilys.model.Computer;
import main.java.com.excilys.ui.CommandLineInterface;

public class RemoveComputer {
	
	// ******* REMOVING COMPUTER METHOD *******
	public static void removeComputer() {
		CommandLineInterface cli = new CommandLineInterface();
		
		System.out.println("\n***********************");
		System.out.println("** Removing Computer **");
		System.out.println("***********************\n");

		System.out.print(
				"Please enter the ID of the computer you want to remove. You can enter '0' to cancel the remove and come back to the menu : ");
		String input = cli.getUserKeyboard().nextLine().trim();

		while ((StringUtils.isNullOrEmpty(input) || !StringUtils.isStrictlyNumeric(input)) && !input.equals("0")) {
			System.out.print(
					"Sorry Mr, the ID of your computer is invalid. Please enter a valid ID for your computer or entrer '0' to cancel the remove and come back to the menu : ");
			input = cli.getUserKeyboard().nextLine().trim();
		}

		if (!input.equals("0")) {
			try {
				Computer computer = cli.getComputerDAO().find(Integer.parseInt(input));
				System.out.println(computer.toString());
				System.out.print("Are you sure you want to remove this computer ? (Y\\N) : ");
				input = cli.getUserKeyboard().nextLine().trim();

				while (!input.equals("Y") && !input.equals("N")) {
					System.out.print(
							"Enter 'Y' to confirm the suppression of your computer from your Database or 'N' to cancel the remove and come back to the menu : ");
					input = cli.getUserKeyboard().nextLine().trim();
				}
				if (input.equals("Y")) {
					cli.getComputerDAO().remove(computer);

					System.out.println("\n************************************");
					System.out.println("** Computer removed successfuly ! **");
					System.out.println("************************************\n\n");

				} else {
					System.out.println();
					return;
				}
			} catch (DAOException e) {
				System.out.println("Sorry Mr, this computer doesn't exist in your Database.\n");
			}
		}
	}
}