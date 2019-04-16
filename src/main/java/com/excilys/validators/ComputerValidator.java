package main.java.com.excilys.validators;

import main.java.com.excilys.exceptions.ComputerException;
import main.java.com.excilys.models.Computer;

public class ComputerValidator {
	
	public void validateComputer(Computer computer) throws ComputerException {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString).test(computer.getName())
				.getFieldNameIfInvalid("Sorry, this computer name is not valid...").orElse(""));
		stringBuilder.append(ValidatorUtil.validDateComputer.test(computer)
				.getFieldNameIfInvalid("Sorry, these computer dates are not valid...s").orElse(""));

		String errors = stringBuilder.toString();
		if (!errors.isEmpty()) {
			throw new ComputerException(errors);
		}
	}
}
