package com.excilys.cdb;

import java.sql.Timestamp;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Manufacturers;
import com.excilys.ui.CommandLineInterface;

public class ComputerDataBase {
	
	public static void main(String[] args) throws Exception {
		// Test 1
		String dateIn = "28/01/1995";
		Timestamp introducedDate = Computer.stringToTimestamp(dateIn);
		
		String dateOut = "12/07/2018";
		Timestamp discontinuedDate = Computer.stringToTimestamp(dateOut);
		
		Company dell = new Company(Manufacturers.Dell.toString());
		Computer jarvis = new Computer("Latitude 5580",dell , introducedDate, discontinuedDate);
		
		System.out.println(jarvis.toString());
		System.out.println(dell.toString());
		
		// Test 2
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		commandLineInterface.start();
		System.exit(0);
	}
}