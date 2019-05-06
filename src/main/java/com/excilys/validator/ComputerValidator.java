package com.excilys.validator;

import java.sql.Timestamp;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Computer;

public class ComputerValidator {
	
// ******** VALIDATOR *******
	/**
	 * Computer validator
	 * @param computer Computer
	 * @throws ValidatorException
	 */
	public static void validateComputer(Computer computer) throws ValidatorException {
		Timestamp introducedDate = computer.getIntroducedDate();
		Timestamp discontinuedDate = computer.getDiscontinuedDate();
		
		if (computer.getName() == null) {
			throw new ValidatorException("NullPointerNameException : This computer has no name.");
		}

		if (introducedDate == null && discontinuedDate != null) {
			throw new ValidatorException("IncoherentDateException : "
					+ "Discontinued date of this computer is not null while introduced date is.");
		}

		if (introducedDate != null && discontinuedDate != null) {
			if (introducedDate.after(discontinuedDate)) {
				throw new ValidatorException("MismatchDateException : Discontinued date is before introduced date.");
			}
		}
	}
}