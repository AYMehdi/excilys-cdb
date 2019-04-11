package main.java.com.excilys.mapper;

import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.CompanyDTO;

public class CompanyBeanMapper {
	
	public Company map(CompanyDTO companyDTO) {
		Company company = new Company();
		
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		
		return (company);
	}
}
