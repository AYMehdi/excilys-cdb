package com.excilys.validator;

import com.excilys.exception.ValidatorException;
import com.excilys.model.Company;

public class CompanyValidator {
	
// ******** VALIDATOR *******
	/**
	 * Company validator
	 * @param company Company
	 * @throws ValidatorException
	 */
	public static void validateComputer(Company company) throws ValidatorException {
		if (company.getName() == null) {
			throw new ValidatorException("NullPointerNameException : This company has no name.");
		}
	}
}