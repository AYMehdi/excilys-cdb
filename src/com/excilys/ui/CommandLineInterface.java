package com.excilys.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.excilys.command.Action;
import com.excilys.command.AddComputer;
import com.excilys.command.CompaniesList;
import com.excilys.command.ComputersList;
import com.excilys.command.RemoveComputer;
import com.excilys.command.ShowComputers;
import com.excilys.command.UpdateComputer;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.DAOFactory;


public class CommandLineInterface {
	
	// ******* VARIABLES *******
	private DAOFactory daoFactory;
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	private DateFormat dateFormat;
	private Scanner userKeyboard;
	
	// ******* PRINCIAPAL METHODS *******
	public CommandLineInterface() {
		this.daoFactory = DAOFactory.getInstance();
		this.companyDAO = daoFactory.getCompanyDao();
		this.computerDAO = daoFactory.getComputerDao();
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.userKeyboard = new Scanner(System.in);
	}

	public void start() {
		Action action = null;

		System.out.println("\n***********************************************************");
		System.out.println("***********************************************************");
		System.out.println("***                                                     ***");
		System.out.println("***     Welcome in your Computer DataBase Manager !     ***");
		System.out.println("***      Happy to see you again Mr AHMED-YAHIA !     	***");
		System.out.println("***                                                     ***");
		System.out.println("***********************************************************");
		System.out.println("***********************************************************\n\n");

		while (action != Action.EXIT) {
			menu();

			try {
				action = Action.getAction(userKeyboard.nextLine().trim());
				System.out.println();

				switch (action) {
				case LIST_COMPUTERS:
					ComputersList.computersList();
					break;
				case LIST_COMPANIES:
					CompaniesList.companiesList();
					break;
				case ADD_COMPUTER:
					AddComputer.addComputer();
					break;
				case UPDATE_COMPUTER:
					UpdateComputer.updateComputer();
					break;
				case REMOVE_COMPUTER:
					RemoveComputer.removeComputer();
					break;
				case SHOW_COMPUTER:
					ShowComputers.showComputer();
					break;
				case EXIT:
					System.out.println("\n***********************************************************");
					System.out.println("***********************************************************");
					System.out.println("***                                                     ***");
					System.out.println("***            Goodbye Mr AHMED-YAHIA !                 ***");
					System.out.println("***                                                     ***");
					System.out.println("***********************************************************");
					System.out.println("***********************************************************\n");
					userKeyboard.close();
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(
						"Sorry Mr, this is not an action.\n"
						+ "Please enter an integer between 1 and 6 in order to perform an action.\n"
						+ "Enter 0 to exit your Computer DataBase Manager.\n");
			}
		}
	}

	public void menu() {
		System.out.println("What do you want to do Mr ?\n");
		System.out.println("List Computers : 1");
		System.out.println("List Companies : 2");
		System.out.println("Add a Computer into the Database : 3");
		System.out.println("Update a Computer in the Database : 4");
		System.out.println("Remove a Computer from the Database : 5");
		System.out.println("Show Computer Informations : 6\n");
		System.out.println("Exit : 0\n");
	}

	// ******* GETTERS *******
	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public Scanner getUserKeyboard() {
		return userKeyboard;
	}
	
	// ******* SETTERS *******
	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}
	
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setUserKeyboard(Scanner userKeyboard) {
		this.userKeyboard = userKeyboard;
	}	
}