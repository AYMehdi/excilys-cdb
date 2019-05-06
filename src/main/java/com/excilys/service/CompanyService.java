package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.model.Company;
import com.excilys.model.dto.CompanyDTO;
import com.excilys.model.mapper.CompanyMapper;
import com.excilys.persistence.CompanyDAO;

@Service
public class CompanyService {
	
// ******** VARIABLE *******
	@Autowired
	CompanyDAO companyDao;

// ******** CONSTRUCTOR *******
	/**
	 * Constructor without parameter
	 */
	public CompanyService() {
		super();
	}

// ******** SERVICE *******
	/**
	 * List all companies in the database
	 * @return companiesDTO List<CompanyDTO>
	 * @throws SQLException
	 */
	public List<CompanyDTO> listeCompagnies() throws SQLException {
		List<Company> companies = companyDao.getAll();
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		
		for (Company company : companies) {
			companiesDTO.add(CompanyMapper.companyToCompanyDTO(company));
		}
		
		return companiesDTO;
	}
}