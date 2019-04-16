package main.java.com.excilys.cdb;
 
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.Computer;
import main.java.com.excilys.models.Manufacturers;
import main.java.com.excilys.ui.CommandLineInterface;

public class ComputerDataBase {
	
	public static void main(String[] args) throws Exception {
		Logger logger = Logger.getLogger(ComputerDataBase.class);
		logger.info("\n ***** Heureux de vous revoir Monsieur ! ****** \n");
		
		// Test 1 :
		String dateIn = "28/01/1995";
		Timestamp introducedDate = Computer.stringToTimestamp(dateIn);
		
		String dateOut = "12/07/2018";
		Timestamp discontinuedDate = Computer.stringToTimestamp(dateOut);
		
		Company dell = new Company(Manufacturers.Dell.toString());
		Computer jarvis = new Computer("Latitude 5580",dell , introducedDate, discontinuedDate);
		
		System.out.println(jarvis.toString());
		System.out.println(dell.toString() + "\n");
		
		// Test 2 :
		logger.info("App starting");
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		commandLineInterface.start();
		logger.info("App closing");
		System.exit(0);		
	}
}