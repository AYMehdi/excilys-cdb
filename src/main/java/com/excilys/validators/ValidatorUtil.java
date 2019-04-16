package main.java.com.excilys.validators;

import main.java.com.excilys.models.Computer;

public class ValidatorUtil {
	
	public static GenericValidation<Computer> validDateComputer = GenericValidation.from((Computer c) 
			-> {
				boolean validComputer = true;
				if (c.getIntroducedDate() != null && c.getDiscontinuedDate() != null) {
					if (c.getDiscontinuedDate().getTime() - c.getIntroducedDate().getTime() < 0) {
						validComputer = false;
					}
				}
				return validComputer;
			});
	
	public static GenericValidation<String> validDateString = GenericValidation.from((String s) 
			-> s == null ? false : s.matches("(^$|[1-9][0-9]{3}[/][0-9]{2}[/][0-9]{2}$)"));
	
	public static GenericValidation<String> notNullString = GenericValidation.from((String s) 
			-> s != null);
	
	public static GenericValidation<String> notEmptyString = GenericValidation.from((String s) 
			-> !s.equals(""));
}
