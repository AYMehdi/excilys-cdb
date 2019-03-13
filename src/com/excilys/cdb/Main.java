package com.excilys.cdb;

import com.excilys.computer.Computer;
import java.util.Date;

public class Main {
	
	public static void main(String[] args) throws Exception {
		// Test
		String dateIn = "2017-09-29";
		Date introducedDate = Computer.StringToDate(dateIn);
		
		String dateOut = "2019-02-14";
		Date discontinuedDate = Computer.StringToDate(dateOut);
		
		Computer jarvis = new Computer("Latitude 5580", "Dell", introducedDate, discontinuedDate);
		System.out.println(jarvis.toString());
	}
}