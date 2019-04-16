package main.java.com.excilys.mappers;

import main.java.com.excilys.models.Company;
import main.java.com.excilys.models.CompanyDTO;

public class CompanyDTOMapper {
	
	public CompanyDTO map(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		
		return companyDTO;
	}
}