package main.java.com.excilys.mappers;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.CompanyDTO;

public class CompanyBeanMapper {
	
	public Company map(CompanyDTO companyDTO) {
		Company company = new Company();
		
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		
		return (company);
	}
}
