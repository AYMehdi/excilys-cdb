package main.java.com.excilys.validators;

import main.java.com.excilys.exceptions.CompanyException;
import main.java.com.excilys.models.Company;

public class CompanyValidator {
	
	public void validateCompany(Company company) throws CompanyException {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(ValidatorUtil.notNullString.and(ValidatorUtil.notEmptyString).test(company.getName())
				.getFieldNameIfInvalid("Sorry, this company name is not valid...").orElse(""));

		String errors = stringBuilder.toString();
		if (!errors.isEmpty()) {
			throw new CompanyException(errors);
		}
	}
}
