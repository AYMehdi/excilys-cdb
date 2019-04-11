package main.java.com.excilys.mapper;

import main.java.com.excilys.model.Company;
import main.java.com.excilys.model.CompanyDTO;

public class CompanyDTOMapper {
	
	public CompanyDTO map(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		
		return companyDTO;
	}
}