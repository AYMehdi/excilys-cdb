package com.excilys.view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.exception.DAOException;
import com.excilys.exception.ModelException;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.builder.ComputerBuilder;
import com.excilys.model.mapper.TimestampMapper;
import com.excilys.persistence.CompanyDAO;
import com.excilys.persistence.ComputerDAO;
import com.excilys.persistence.ConnectionDAO;

public class CommandLineInterface {

// ******* VARIABLES *******
	ComputerDAO computerDAO;
	CompanyDAO companyDAO;
	Integer choice;
	static Scanner scanner;
	
// ******* CONSTRUCTOR *******
	/**
	 * Constructor with two parameters
	 * @param computerDAO ComputerDao
	 * @param companyDAO CompanyDAO
	 * @throws ParseException 
	 * @throws SQLException
	 * @throws DAOException
	 * @throws ModelException 
	 */
	public CommandLineInterface(ComputerDAO computerDAO, CompanyDAO companyDAO) throws ParseException, SQLException, DAOException, ModelException {
		super();
		welcome();
		this.computerDAO = computerDAO;
		this.companyDAO = companyDAO;
		scanner = new Scanner(System.in);
		mainConsole();
	}

//******* GETTERS *******	
	/**
	 * @return computerDAO ComputerDAO
	 */
	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}
		
	/**
	 * @return companyDAO CompanyDAO
	 */
	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	/**
	 * @return choice int
	 */
	public int getChoice() {
		return choice;
	}

//******* SETTERS *******
	/**
	 * @param computerDAO ComputerDAO
	 */
	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}

	/**
	 * @param companyDAO CompanyDAO
	 */
	public void setCompanyDAO(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
		
	/**
	 * @param choice int
	 */
	public void setChoice(int choice) {
		this.setChoice(choice);
	}	
	
// ******* CONSOLES *******
	/**
	 * Main console for user-machine interaction
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ModelException 
	 */
	public void mainConsole() throws SQLException, DAOException, ParseException, ModelException {
		do {
			mainMenu();
			System.out.print("Your choice : ");
			choice = scanner.nextInt();
			System.out.println();
			int actionsNumber = MainAction.values().length;
	
			if (choice < 0 || choice > actionsNumber ) {
				if(!choice.equals("0")) {
					System.out.println("Invalid choice. Please try again"
						+ "\n_______________________________________________________\n\n");
					mainConsole();
					return;
				}
			}
			else {
				MainAction actionChoice = MainAction.values()[choice];
	
				switch(actionChoice) {
				case COMPUTER_MENU:
					computerConsole();
					break;
				case COMPANY_MENU:
					companyConsole();
					break;
				case QUITTER:
					System.out.println("\n***********************************************************");
					System.out.println("***********************************************************");
					System.out.println("***                                                     ***");
					System.out.println("***            Goodbye Mr AHMED-YAHIA !                 ***");
					System.out.println("***                                                     ***");
					System.out.println("***********************************************************");
					System.out.println("***********************************************************\n");
					scanner.close();
					ConnectionDAO.getInstance().close();
					return;
				default:
					System.out.println("Invalid choice. Please try again"
							+ "\n_______________________________________________________\n\n");
				}
			}
		} while(choice != 0);
	}
	
	/**
	 * Computer console for user-machine interaction
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void computerConsole() throws SQLException, DAOException, ParseException, ModelException {
		computerMenu();
		System.out.print("Your choice : ");
		choice = scanner.nextInt();
		System.out.println();
		int actionsNumber = ComputerAction.values().length;

		if (choice < 0 || choice > actionsNumber ) {
			System.out.println("Invalid choice. Please try again"
					+ "\n_______________________________________________________\n\n");
			computerConsole();
			return;
		}
		else {
			ComputerAction choix = ComputerAction.values()[choice];

			switch(choix) {
			case LIST_COMPUTERS: 
				listComputers();
				break;
			case SELECT_COMPUTERS:
				selectComputers();
				break;
			case SHOW_COMPUTER_DETAILS:
				showComputerDetails();
				break;
			case ADD_COMPUTER:
				addComputer();
				break;
			case UPDATE_COMPUTER:
				updateComputer();
				break;
			case REMOVE_COMPUTER:
				removeComputer();
				break;
			case BACK:
				System.out.println("\n******* Back to main menu *******\n");
				mainConsole();
				return;
			default :
				System.out.println("Invalid choice. Please try again"
						+ "\n_______________________________________________________\n\n");
			}
			computerConsole();
			return;
		}
	}
	
	/**
	 * Company console for user-machine interaction
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void companyConsole() throws SQLException, DAOException, ParseException, ModelException {
		companyMenu();
		System.out.print("Your choice : ");
		choice = scanner.nextInt();
		System.out.println();
		int actionsNumber = CompanyAction.values().length;

		if (choice < 0 || choice > actionsNumber ) {
			System.out.println("Invalid choice. Please try again"
					+ "\n_______________________________________________________\n\n");
			computerConsole();
			return;
		}
		else {
			CompanyAction choix = CompanyAction.values()[choice];

			switch(choix) {
			case LIST_COMPANIES: 
				listCompanies();
				break;
			case SELECT_COMPANIES:
				selectCompanies();
				break;
			case SHOW_COMPANY_DETAILS:
				showCompanyDetails();
				break;
			case ADD_COMPANY:
				addCompany();
				break;
			case UPDATE_COMPANY:
				updateCompany();
				break;
			case REMOVE_COMPANY:
				removeCompany();
				break;
			case BACK:
				System.out.println("\n******* Back to main menu *******\n");
				mainConsole();
				return;
			default :
				System.out.println("Invalid choice. Please try again"
						+ "\n_______________________________________________________\n\n");
				
			}
			companyConsole();
			return;
		}
	}
	
// ******* MENUS *******
	/**
	 * J.A.R.V.I.S welcome message
	 */
	public static void welcome() {
		System.out.println("\n***********************************************************");
		System.out.println("***********************************************************");
		System.out.println("***                                                     ***");
		System.out.println("***     Welcome in your Computer DataBase Manager !     ***");
		System.out.println("***      Happy to see you again Mr AHMED-YAHIA !     	***");
		System.out.println("***                                                     ***");
		System.out.println("***********************************************************");
		System.out.println("***********************************************************\n\n");
	}
	
	/**
	 *  Main console menu
	 */
	private void mainMenu() {
		System.out.println("****************************");
		System.out.println("What do you want to do Mr ?");
		System.out.println("****************************\n");
		System.out.println("Computer menu............ 1");
		System.out.println("Company menu..............2\n");
		System.out.println("Exit CDB..................0");
		System.out.println("****************************\n");
	}
	
	/**
	 *  Computer console menu
	 */
	private void computerMenu() {
		System.out.println("*******************************************");
		System.out.println("Computer menu : What do you want to do Mr ?");
		System.out.println("*******************************************\n");
		System.out.println("List computers...........................1");
		System.out.println("Select computers.........................2");
		System.out.println("Show computer informations...............3");
		System.out.println("Add a computer into the database.........4");
		System.out.println("Update a computer from the database......5");
		System.out.println("Remove a omputer from the database.......6\n");
		System.out.println("Back to main menu........................0");
		System.out.println("*******************************************\n");
	}
	
	/**
	 *  Company console menu
	 */
	private void companyMenu() {
		System.out.println("******************************************");
		System.out.println("Company menu : What do you want to do Mr ?");
		System.out.println("******************************************\n");
		System.out.println("List companies..........................1");
		System.out.println("Select companies........................2");
		System.out.println("Show company informations...............3");
		System.out.println("Add a company into the database.........4");
		System.out.println("Update a company from the database......5");
		System.out.println("Remove a company from the database......6\n");
		System.out.println("Back to main menu.......................0");
		System.out.println("******************************************\n");
	}

// ******* METHODS *******
	/**
	 * List all computers in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public void listComputers() throws SQLException, DAOException {
		System.out.println("\n***********************");
		System.out.println("** Listing Computers **");
		System.out.println("***********************\n");
		List<Computer> computers = computerDAO.getAll();
		System.out.println("Computers in the database : ");
		for(Computer computer : computers) {
			System.out.println("Computer ID : " + computer.getId()
			+"\n	Name : " + computer.getName());
		}
		System.out.println("\nTotal : " + computers.size() + " computers");
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}
	
	/**
	 * List all companies in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 */
	public void listCompanies() throws SQLException, DAOException {
		System.out.println("\n***********************");
		System.out.println("** Listing Companies **");
		System.out.println("***********************\n");
		List<Company> companies = companyDAO.getAll();
		System.out.println("Companies in the database : ");
		for(Company company : companies) {
			System.out.println("Company ID : " + company.getId()
			+"\n	Name : " + company.getName());
		}
		System.out.println("\nTotal : " + companies.size() + " companies");
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}

	/**
	 * Select computers in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void selectComputers() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n*************************");
		System.out.println("** Selecting Computers **");
		System.out.println("*************************\n");
		int idFrom = 0;
		int idUntil = -1;
		while (idFrom > idUntil) {
			System.out.print("From ID : ");
			idFrom = scanner.nextInt();
			if (idFrom == 0) {
				System.out.println("\nBack to computer menu\n"
						+ "\n_______________________________________________________");
				this.computerConsole();
			}
			
			System.out.print("Until ID : ");
			idUntil = scanner.nextInt();
			if (idUntil == 0) {
				System.out.println("\nBack to computer menu\n"
						+ "\n_______________________________________________________");
				this.computerConsole();
			}
			
			if(idUntil < idFrom) {
				System.out.println("\nThe second ID imput must be greater than the first one... Let's try again !");
			}
		}

		List<Computer> computers = computerDAO.getById(idFrom, idUntil);
		System.out.println("\nComputers in the database from ID n°" + idFrom + " until ID n°" + idUntil + " :");
		for(Computer computer : computers) {
			System.out.println("Computer ID : " + computer.getId()
			+"\n	Name : " + computer.getName());
		}
		System.out.println("\nTotal : " + computers.size() + " companies");
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}
	
	/**
	 * Select companies in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void selectCompanies() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n*************************");
		System.out.println("** Selecting Companies **");
		System.out.println("*************************\n");
		int idFrom = 0;
		int idUntil = -1;
		while (idFrom > idUntil) {
			System.out.print("From ID : ");
			idFrom = scanner.nextInt();
			if (idFrom == 0) {
				System.out.println("\nBack to company menu\n"
						+ "\n_______________________________________________________");
				this.companyConsole();
			}
			
			System.out.print("Until ID : ");
			idUntil = scanner.nextInt();
			if (idUntil == 0) {
				System.out.println("\nBack to company menu\n"
						+ "\n_______________________________________________________");
				this.companyConsole();
			}
			
			if(idUntil < idFrom) {
				System.out.println("\nThe second ID imput must be greater than the first one... Let's try again !");
			}
		}
	
		List<Company> companies = companyDAO.getById(idFrom, idUntil);
		System.out.println("\nCompanies in the database from ID n°" + idFrom + " until ID n°" + idUntil + " :");
		for(Company company : companies) {
			System.out.println(company);
		}
		System.out.println("\nTotal : " + companies.size() + " companies");
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}

	/**
	 * Show computer description with  a computer ID parameter
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void showComputerDetails() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n**********************");
		System.out.println("** Computer Details **");
		System.out.println("**********************\n");
		System.out.print("Computer ID : ");
		int computerID = scanner.nextInt();
		if (computerID == 0) {
			System.out.println("\nBack to computer menu\n"
					+ "\n_______________________________________________________");
			this.computerConsole();
		}
		
		Optional<Computer> computer = computerDAO.find(computerID);

		if (computer.isPresent()) {
			System.out.println("\n"+computer.get()+"\n");
		} else {
			System.out.println("Sorry, this computer does not exist. Try again.");
		}
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}
	
	/**
	 * Show company description with  a computer ID parameter
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void showCompanyDetails() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n*********************");
		System.out.println("** Company Details **");
		System.out.println("*********************\n");
		System.out.print("Company ID : ");
		int companyID = scanner.nextInt();
		if (companyID == 0) {
			System.out.println("\nBack to company menu\n"
					+ "\n_______________________________________________________");
			this.companyConsole();
		}
		
		Optional<Company> company = companyDAO.find(companyID);

		if (company.isPresent()) {
			System.out.println("\n"+company.get()+"\n");
		} else {
			System.out.println("Sorry, this company does not exist. Try again.");
		}
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}

	/**
	 * Add a computer in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void addComputer() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n*********************");
		System.out.println("** Adding Computer **");
		System.out.println("*********************\n");
		System.out.print("Please enter the name of the computer you want to add (enter 0 to go back) : ");
		
		String computerName = null;
		do {
			computerName = scanner.next();
			if (computerName.equals("0")) {
				System.out.println("\nBack to computer menu\n"
						+ "\n_______________________________________________________");
				this.computerConsole();
			}
			else if (computerName.length() < 3) {
				System.out.print("\nSorry, a computer name must has more than 3 characters...\n"
						+ "Please add an other computer name : ");
			}
		} while (computerName.length() < 3);
		
		System.out.print("Please enter the name of your computer's manufacturer (enter 0 to go back) : ");
		String companyName = scanner.next();
		if (companyName.equals("0")) {
			System.out.println("\nBack to computer menu\n"
					+ "\n_______________________________________________________");
			this.computerConsole();
		}
		
		Optional<Company> company = companyDAO.find(companyName);
		Timestamp introducedDate = TimestampMapper.currentTimeToTimestamp();

		if(company.isPresent()) {
			Computer computer = new ComputerBuilder().empty()
					.withName(computerName)
					.withCompany(company.get())
					.withIntroducedDate(introducedDate)
					.build();
			System.out.print("You are gonna add the computer " + computer.getName() + " manufactured by " + computer.getCompany().getName() + " in your database."
					+ "\nDo you want to add this computer ? (Y/N) : ");
			String validator = scanner.next();
			System.out.println();
			if (validator.equalsIgnoreCase("Y")) { 
				computerDAO.add(computer);
				System.out.println("\n**********************************");
				System.out.println("** Computer added succesfully ! **");
				System.out.println("**********************************\n\n");
			}
		} else {
			System.out.println("\nA computer name must has more than 3 characters.");
			this.addComputer();
		}
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}
	
	/**
	 * Add a company in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void addCompany() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n********************");
		System.out.println("** Adding Company **");
		System.out.println("********************\n");
		System.out.print("Please enter the name of the company you want to add (enter 0 to go back) : ");
		
		String companyName = null;
		do {
			companyName = scanner.next();
			if (companyName.equals("0")) {
				System.out.println("\nBack to company menu\n"
						+ "\n_______________________________________________________");
				this.companyConsole();
			}
			else if (companyName.length() < 3) {
				System.out.print("\nSorry, a company name must has more than 3 characters...\n"
						+ "Please add an other company name : ");
			}
		} while (companyName.length() < 3);
			
		Company company = new Company(companyName);
		System.out.print("You are gonna add the company " + company.getName() + " in your database."
				+ "\nDo you want to add this company ? (Y/N) : ");
		String validator = scanner.next();
		System.out.println();
		if (validator.equalsIgnoreCase("Y")) { 
			companyDAO.add(company);
			System.out.println("\n*********************************");
			System.out.println("** Company added succesfully ! **");
			System.out.println("*********************************\n\n");
		}
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}			

	/**
	 * Update a computer in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void updateComputer() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n***********************");
		System.out.println("** Updating Computer **");
		System.out.println("***********************\n");
		System.out.print("Please enter the ID of the computer you want to update (enter 0 to go back) : ");
		int computerID = scanner.nextInt();
		if (computerID == 0) {
			System.out.println("\nBack to computer menu\n"
					+ "\n_______________________________________________________");
			this.computerConsole();
		}

		Optional<Computer> computer = computerDAO.find(computerID);

		if (computer.isPresent()) {
			System.out.println(computer.get());

			System.out.print("New computer name : ");
			String computerName = null;
			do {
				computerName = scanner.next();
				if (computerName.equals("0")) {
					System.out.println("\nBack to computer menu\n"
							+ "\n_______________________________________________________");
					this.computerConsole();
				}
				else if (computerName.length() < 3) {
					System.out.print("\nSorry, a computer name must has more than 3 characters...\n"
							+ "Please add an other computer name : ");
				}
			} while (computerName.length() < 3);
			computer.get().setName(computerName);

			System.out.print("Manufacturer name : ");
			String companyName = scanner.next();
			if (companyName.equals("0")) {
				System.out.println("\nBack to computer menu\n"
						+ "\n_______________________________________________________");
				this.computerConsole();
			}
			computer.get().setCompany(companyDAO.find(companyName).get());

			if (computer.get().getIntroducedDate() != null && computer.get().getDiscontinuedDate() == null) {
				System.out.print("Discontinued this computer ? (Y/N) : ");
				String answer = scanner.next();
				if (answer.equals("0")) {
					System.out.println("\nBack to computer menu\n"
							+ "\n_______________________________________________________");
					this.computerConsole();
				}
				if(answer.equalsIgnoreCase("Y")) {
					Timestamp discontinuedDate;
					discontinuedDate = TimestampMapper.currentTimeToTimestamp();
					computer.get().setDiscontinuedDate(discontinuedDate);
				}
			}
			Computer updatedComputer = new Computer();
			updatedComputer = computer.get();
			System.out.print("You are gonna update this computer :\n" + computer.get() + "\nOld informations about it will be removed.\n"
					+ "\nDo you want to udate this computer ? (Y/N) : ");
			String validator = scanner.next();
			System.out.println();
			if (validator.equalsIgnoreCase("Y")) { 
				computerDAO.update(updatedComputer);
				System.out.println("\n***********************************");
				System.out.println("** Computer updated succesfully ! **");
				System.out.println("***********************************\n\n");
			}
		} else {
			System.out.println("This computer does not exist. Please try again.");
			this.updateComputer();
		}
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}

	/**
	 * Update a company in the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void updateCompany() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n**********************");
		System.out.println("** Updating Company **");
		System.out.println("**********************\n");
		System.out.print("Please enter the ID of the company you want to update (enter 0 to go back) : ");
		int companyID = scanner.nextInt();
		if (companyID == 0) {
			this.companyConsole();
		}
			
		Optional<Company> company = companyDAO.find(companyID);

		if (company.isPresent()) {
			System.out.println(company.get());
			System.out.print("New company name : ");
			
			String companyName = null;
			do {
				companyName = scanner.next();
				if (companyName.equals("0")) {
					System.out.println("\nBack to company menu\n"
							+ "\n_______________________________________________________");
					this.companyConsole();
				}
				if (companyName.length() < 3) {
					System.out.print("\nSorry, a company name must has more than 3 characters...\n"
							+ "Please enter an other name to update your company : ");
				}
			} while (companyName.length() < 3);
			
			company.get().setName(companyName);
			
			Company updatedCompany = new Company();
			updatedCompany = company.get();
			System.out.print("You are gonna update the company " + company.get() + ".\n"
					+ "Old informations about it will be removed and it will affect all computers associated.\n"
					+ "\nDo you want to udate this company ? (Y/N) : ");
			String validator = scanner.next();
			System.out.println();
			if (validator.equalsIgnoreCase("Y")) { 
				companyDAO.update(updatedCompany);
				System.out.println("\n***********************************");
				System.out.println("** Company updated succesfully ! **");
				System.out.println("***********************************\n\n");
			}
		} else {
			System.out.println("This company does not exist. Please try again.");
			this.updateCompany();
		}
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}

	/**
	 * Remove a computer from the database
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void removeComputer() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n***********************");
		System.out.println("** Removing Computer **");
		System.out.println("***********************\n");
		System.out.print("Please enter the ID of the computer you want to remove (enter 0 to go back) : ");
		int computerID = scanner.nextInt();
		if (computerID == 0) {
			System.out.println("\nBack to computer menu\n"
					+ "\n_______________________________________________________");
			this.computerConsole();
		}
		
		Optional<Computer> computer = computerDAO.find(computerID);
		if (computer.isPresent()) {
			System.out.print("You are gonna remove this computer :\n"
					+ computer.get()
					+ "\nIt could not be undone. Are you sure you want to remove this computer ? (Y/N) : ");
			String validator = scanner.next();
			System.out.println();
			if (validator.equalsIgnoreCase("Y")) { 
				computerDAO.remove(computer.get());
				System.out.println("\n***********************************");
				System.out.println("** Company removed successfuly ! **");
				System.out.println("***********************************\n\n");
			}
		} else {
			System.out.println("This computer does not exist. Please try again.");
			this.removeComputer();
		}
		System.out.println("\nBack to computer menu\n"
				+ "\n_______________________________________________________");
	}
	
	/**
	 * Remove a company and all computers with this manufacturer
	 * @throws SQLException 
	 * @throws DAOException 
	 * @throws ParseException 
	 * @throws ModelException 
	 */
	public void removeCompany() throws SQLException, DAOException, ParseException, ModelException {
		System.out.println("\n**********************");
		System.out.println("** Removing Company **");
		System.out.println("**********************\n");
		System.out.print("Please enter the ID of the company you want to remove (enter 0 to go back) : ");
		int companyID = scanner.nextInt();
		if (companyID == 0) {
			System.out.println("\nBack to company menu\n"
					+ "\n_______________________________________________________");
			this.companyConsole();
		}
		
		Optional<Company> company = companyDAO.find(companyID);
		if (company.isPresent()) {
			System.out.print("You are gonna remove the company " + company.get() + " and all computers associated with.\n"
					+ "It could not be undone. Are you sure you want to remove this company ? (Y/N) : ");
			String validator = scanner.next();
			System.out.println();
			if (validator.equalsIgnoreCase("Y")) { 
				companyDAO.remove(company.get());
				System.out.println("\n***********************************");
				System.out.println("** Company removed successfuly ! **");
				System.out.println("***********************************\n\n");
			}
		}else {
			System.out.println("This company does not exist. Please try again.");
			this.removeCompany();
		}
		System.out.println("\nBack to company menu\n"
				+ "\n_______________________________________________________");
	}
}